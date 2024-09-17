package tenista.services

import cache.CacheTenistasImpl
import com.github.michaelbull.result.*
import org.lighthousegames.logging.logging
import tenista.errors.TenistaError
import tenista.models.Tenista
import tenista.repositories.TenistaRepository
import tenista.storages.StorageCsv
import tenista.storages.leerFichero
import java.io.File

private val logger = logging()

class TenistaServiceImpl(
    private val repository : TenistaRepository,
    private val cache : CacheTenistasImpl,
    private val storage : StorageCsv

    ) : TenistasService , leerFichero {
    override fun getAll(): Result<List<Tenista>, TenistaError> {
        logger.debug { "Obteniendo todos los tenistas" }
        return Ok(repository.getAll())
    }

    override fun getById(id: Int): Result<Tenista, TenistaError> {
        logger.debug { "Obteniendo tenista por id $id" }
        return repository.getById(id)?.let {
            cache.put(it.id.toLong(),it)
            logger.debug { "Guardando id en la cache" }
            Ok(it) }?:
        Err(TenistaError.TenistaErrorValida("Error al obtener la por id $id"))
    }

    override fun save(tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Guardando tenista" }
        return Ok(repository.save(tenista)).andThen {
            cache.put(it.id.toLong(),it)
            logger.debug { "Guardando id en la cache" }
            Ok(it)
            Err(TenistaError.TenistaErrorValida("Error al guardar el tenista"))
        }
    }

    override fun update(id: Int, tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Actualizando producto por id $id" }
        return repository.update(id,tenista)?.let {
            cache.put(id.toLong(),it)
            Ok(it)}?:
        Err(TenistaError.TenistaErrorValida("Error al actualizando por id $id"))
    }

    override fun deleteById(id: Int): Result<Tenista, TenistaError> {
        logger.debug { "Eliminando tenista por id $id" }
        return repository.deleteById(id)?.let {
            cache.remove(id.toLong())
            Ok(it)}
        ?: Err(TenistaError.TenistaErrorValida("Error al borrar por id $id"))
    }

    override fun leer(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Leyendo fichero $file" }
        return storage.leer(file)
            .onSuccess {
                it.forEach { repository.save(it) }
                Ok(it)
            }
    }
}


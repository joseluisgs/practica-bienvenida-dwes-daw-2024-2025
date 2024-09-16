package org.example.tenista.services

import cache.CacheTenistaImpl
import com.github.michaelbull.result.*
import org.example.persona.errors.TenistaError
import org.example.persona.models.Tenista
import org.example.persona.repositories.TenistaRepository
import org.lighthousegames.logging.logging
import tenista.storage.StorageCsv
import tenista.storage.StorageJson
import java.io.File


private val logger = logging()

class TenistaServiceImpl(
    private val repository: TenistaRepository,
    private val cache: CacheTenistaImpl,
    private val storageCsv: StorageCsv,
    private val storageJson: StorageJson
):TenistaService {

    override fun getAll(): Result<List<Tenista>, TenistaError> {
        logger.debug { "Obteniendo todos los tenistas" }
        return Ok(repository.getAll())
    }

    override fun getById(id: Int): Result<Tenista, TenistaError> {
        logger.debug { "Obtendo los tenista por id $id" }
        return repository.getById(id)?.let {
            cache.put(it.id.toLong(),it)
            Ok(it) }?:
        Err(TenistaError.TenistaErrorValida("Error al obtener la por id $id"))
    }

    override fun save(tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Guardando tenista: $tenista" }
        return Ok(repository.save(tenista)).andThen { s ->
            logger.debug { "Guardando en la cache" }
            cache.put(tenista.id.toLong(),s)
        }
    }

    override fun update(id: Int, tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Actualizando tenista por id $id" }
        return repository.update(id,tenista)?.let {
            cache.put(id.toLong(),it)
            Ok(it)
        }?:
        Err(TenistaError.TenistaErrorValida("Error al actualizando por id $id"))
    }

    override fun deleteById(id: Int): Result<Tenista, TenistaError> {
        logger.debug { "Eliminando tenista por id $id" }
        return repository.deleteById(id)?.let {
            cache.remove(id.toLong())
            Ok(it)
        }
            ?: Err(TenistaError.TenistaErrorValida("Tenista no eliminado por id $id"))
    }

    override fun loadJson(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Cargando fichero $file" }
        return storageJson.load(file)
            .onSuccess {
                Ok(it)
            }
    }

    override fun storeJson(file: File, listTenistas: List<Tenista>): Result<Unit, TenistaError> {
        logger.debug { "Salvando en json" }
        return storageJson.store(file, listTenistas)
    }

    override fun loadCsv(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Cargando fichero" }
        return storageCsv.load(file)
            .onSuccess {
                it.forEach { repository.save(it) }
                Ok(it)
            }
    }

    override fun storeCsv(file: File, listTenistas: List<Tenista>): Result<Unit, TenistaError> {
        logger.debug { "Salvando en csv" }
        return storageCsv.store(file, listTenistas)
    }

    override fun obtenerTenistaConMasRango(): Tenista {
        logger.debug { "Obteniendo tenista con mas rango" }
        return repository.tenistaMasRango()
    }


}
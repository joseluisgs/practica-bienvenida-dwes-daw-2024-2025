package tenistas.servicies

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import org.lighthousegames.logging.logging
import tenistas.cache.CacheTenistaImpl
import tenistas.errors.TenistasError
import tenistas.models.Tenista
import tenistas.repositories.TenistaRepository
import tenistas.storage.TenistaStorageStore
import tenistas.storage.TenistasStorageExport
import java.io.File
private val logger= logging()
class TenistasServiceImpl(
    private val repository: TenistaRepository,
    private val cache: CacheTenistaImpl,
    private val store: TenistaStorageStore,
    private val export:TenistasStorageExport
):TenistasService {
    override fun getAll(): Result<List<Tenista>, TenistasError> {
        logger.debug { "Obteniendo todos los tenistas" }
        return Ok(repository.findAll())
    }

    override fun getById(id: Long): Result<Tenista, TenistasError> {
        return cache.get(id).mapBoth(
            success = {
                logger.debug { "Tenista encontrado en cache" }
                Ok(it)
            },
            failure = {
                logger.debug { "Tenista no encontrado en cache" }
                repository.findById(id)
                    ?.let { Ok(it) }
                    ?: Err(TenistasError.NoEncontrado("Tenista no encontrado con id: $id"))
            }
        )
    }

    override fun create(tenista: Tenista): Result<Tenista, TenistasError> {
        logger.debug { "Guardando tenista $tenista" }

        repository.save(tenista).also {  p ->
            cache.put(p.id, p )
        }
        return  Ok(tenista)
    }

    override fun update(id: Long, tenista: Tenista): Result<Tenista, TenistasError> {
        logger.debug { "Actualizando tenista con id: $id" }
        return repository.update(id, tenista)
            ?.let {
                cache.put(id,tenista)
                Ok(it)
            }
            ?: Err(TenistasError.NoActualizado("No se ha podido actualizar el tenista con id: $id"))


    }

    override fun deleteById(id: Long): Result<Tenista, TenistasError> {
        logger.debug { "Eliminado tenista con id: $id" }
        return repository.delete(id)
            ?.let {
                Ok(it)
            }
            ?: Err(TenistasError.NoActualizado("No se ha podido eliminar el tenista con id: $id"))
    }

    override fun import(file: File): Result<List<Tenista>, TenistasError> {
        logger.debug { "Cargando tenistas desde CSV" }
        return store.load(file).mapBoth(
            success = {
                it.forEach{ p->
                    repository.save(p)
                }
                Ok(it)
            },
            failure = {
                Err(it)
            }
        )
    }

    override fun export(file: File, list: List<Tenista>): Result<Long, TenistasError> {
        logger.debug { "Guardando tenistas en fichero" }
        return export.store(file,list)
    }
}
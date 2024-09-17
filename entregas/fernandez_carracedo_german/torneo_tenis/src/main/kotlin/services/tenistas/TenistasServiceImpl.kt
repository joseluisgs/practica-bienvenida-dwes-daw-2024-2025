package org.example.services.tenistas

import com.github.michaelbull.result.*
import org.example.cache.TenistaCache
import org.example.errors.tenistas.TenistaError
import org.example.models.Tenista
import org.example.repositories.tenista.TenistaRepository
import org.example.services.storage.tenista.TenistaFileStorageCsv
import org.example.validators.TenistaValidator
import org.lighthousegames.logging.logging

private val logger = logging()

class TenistasServiceImpl(
    private val tenistaRepository: TenistaRepository,
    private val tenistaCache: TenistaCache,
    private val tenistaValidator: TenistaValidator,
    private val storageTenistasCsv: TenistaFileStorageCsv
): TenistasService {
    override fun getAll(): Result<List<Tenista>, TenistaError> {
        logger.debug { "Obteniendo todos los tenistas" }
        val tenistas: MutableList<Tenista> = mutableListOf()
        tenistaRepository.findAll().forEach { tenistas.add(it) }
        return Ok(tenistas)    }

    override fun getById(id: Long): Result<Tenista, TenistaError> {
        logger.debug { "Obteniendo tenista con id: $id" }
        return tenistaCache.get(id).mapBoth(
            success = {
                logger.debug { "Tenista encontrado en cache" }
                Ok(it)
            },
            failure = {
                logger.debug { "Tenista no encontrado en cache" }
                tenistaRepository.findById(id)
                    ?.let {
                        logger.debug { "Guardando en la cache" }
                        tenistaCache.put(id, it)
                        Ok(it)
                    }?: Err(TenistaError.TenistaNoEncontrado("Tenista no encontrado con id: $id"))
            }
        )
    }

    override fun save(tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Guardando tenista: $tenista" }
        return tenistaValidator.validate(tenista).andThen {
                c ->
            logger.debug { "Guardando en cache" }
            tenistaCache.put(c.id, c)
            Ok(tenistaRepository.save(c))
        }
    }

    override fun update(id: Long, tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Actualizando tenista con id: $id" }
        return tenistaValidator.validate(tenista).andThen { p ->
            tenistaRepository.update(id, p)
                ?.let {
                    logger.debug { "Guardando en la cache" }
                    tenistaCache.put(id, it)
                    Ok(it)
                }
                ?: Err(TenistaError.TenistaNoActualizado("Tenista no actualizado con id: $id"))
        }
    }

    override fun delete(id: Long): Result<Tenista, TenistaError> {
        logger.debug { "Borrando tenista con id: $id" }
        return tenistaRepository.delete(id)
            ?.let {
                logger.debug { "Eliminando de la cache" }
                tenistaCache.remove(id)
                Ok(it)
            } ?: Err(TenistaError.TenistaNoEliminado("Tenista no eliminado con id: $id"))
    }

    override fun deleteAllTenistas(): Result<Unit, TenistaError> {
        logger.debug { "Borrando todos los tenistas" }
        tenistaRepository.deleteAll()
        tenistaCache.clear()
        return Ok(Unit)
    }

    override fun getTenistaByRanking(ranking: Long): Result<Tenista, TenistaError> {
        logger.debug { "Obteniendo tenista por ranking: $ranking" }
        return tenistaRepository.findByRanking(ranking)
            ?.let { Ok(it) }
            ?: Err(TenistaError.TenistaNoEncontrado("Tenista no encontrado con ranking: $ranking"))
    }

    override fun getTenistasByPais(pais: String): Result<List<Tenista>, TenistaError> {
        logger.debug { "Obteniendo todos los tenistas de $pais" }
        val tenistas: MutableList<Tenista> = mutableListOf()
        tenistaRepository.findByPais(pais).forEach { tenistas.add(it) }
        return Ok(tenistas)
    }

    override fun loadTenistasFromCsv(fileName: String): List<Tenista> {
        logger.debug {"Cargando tenistas desde CSV: $fileName"}
        return storageTenistasCsv.readFromFile(fileName)

    }


}
package org.example.tenistas.services

import com.github.michaelbull.result.*
import org.example.cache.CacheImpl
import org.example.tenistas.errors.TenistaError
import org.example.tenistas.models.Tenista
import org.example.tenistas.repositories.TenistaRepository
import org.example.tenistas.repositories.logger
import org.example.tenistas.storage.genericStorage.TenistaStorages

class TenistaServiceImpl(
    private val tenistaRepository: TenistaRepository,
    private val cache: CacheImpl
): TenistaService {
    override fun getAll(): Result<List<Tenista>, TenistaError> {
        logger.debug { "Obteniendo todos los tenistas" }
        val tenistas: MutableList<Tenista> = mutableListOf()
        tenistaRepository.findAll().forEach { tenistas.add(it) }
        return if (tenistas.size > 0) {
            logger.debug { "Tenistas encontrados" }
            Ok(tenistas)
        } else {
            logger.info { "Tenistas no encontrados" }
            Err(TenistaError.TenistaNoEncontrado("No hay tenistas"))
        }
    }

    override fun getTenistaById(id: Int): Result<Tenista, TenistaError> {
        logger.debug { "Obteniendo el tenista con id $id" }
        return cache.get(id).mapBoth(
            success = {
                logger.debug { "Tenista encontrado en cache" }
                Ok(it)
            },
            failure = {
                logger.info { "Tenista no encontrado en cache" }
                tenistaRepository.findById(id)
                    ?.let { Ok(it) }?.andThen { tenista ->
                        logger.debug { "Guardando en cache" }
                        cache.put(tenista.id, tenista)
                    }
                    ?: Err(TenistaError.TenistaNoEncontrado("Tenista no encontrado con id: $id"))
            }
        )
    }

    override fun getTenistaByCountry(item: String): Result<List<Tenista>, TenistaError> {
        logger.debug { "Obteniendo todos los tenistas por pais: ${item}" }
        val tenistas: MutableList<Tenista> = mutableListOf()
        tenistaRepository.findByCountry(item).forEach { tenistas.add(it) }
        return if (tenistas.size > 0) Ok(tenistas) else {
            logger.info { "Tenistas no encontrado" }
            Err(TenistaError.TenistaNoEncontrado("No hay tenistas que pertenezcan al país: $item"))
        }
    }

    override fun getTenistaByRanking(item: Int): Result<List<Tenista>, TenistaError> {
        logger.debug { "Obteniendo todos los tenistas por puntos de ranking: ${item}" }
        val tenistas: MutableList<Tenista> = mutableListOf()
        tenistaRepository.findAll().forEach { tenistas.add(it) }
        return if (tenistas.size > 0) Ok(tenistas) else Err(TenistaError.TenistaNoEncontrado("No hay tenistas con $item puntos de ranking"))
    }

    override fun saveTenista(item: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Guardando tenista: $item" }
        return Ok(tenistaRepository.save(item)).andThen { tenista ->
            logger.debug { "Guardando en cache" }
            cache.put(tenista.id, tenista)
        }
    }

    override fun updateTenista(id: Int, item: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Actualizando tenista con id: $id" }
        return tenistaRepository.update(id, item)?.let {
            Ok(it).andThen { tenista ->
                cache.put(id, tenista)
            }
        } ?: Err(TenistaError.TenistaNoActualizado("Tenista no actualizado con id: $id"))
    }

    override fun deleteTenista(id: Int): Result<Tenista, TenistaError> {
        logger.debug { "Borrando tenista con id: $id" }
        return tenistaRepository.delete(id)
            ?.let {
                logger.debug { "Eliminando de la cache" }
                cache.remove(id)
                Ok(it)
            }
            ?: Err(TenistaError.TenistaNoEliminado("Tenista no eliminado con id: $id"))
    }

    override fun deleteAllTenistas(): Result<Unit, TenistaError> {
        logger.debug { "Borrando todos los tenistas" }
        tenistaRepository.deleteAll().also {
            logger.debug { "Eliminando de la cache" }
            cache.clear()
            return Ok(it)
        }
    }
}
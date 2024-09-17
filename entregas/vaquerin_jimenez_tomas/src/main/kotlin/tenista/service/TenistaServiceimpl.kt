package org.example.tenista.service

import dev.tomas.tenista.repository.TenistaRepository
import org.example.tenista.models.Tenista
import org.example.tenista.repository.TenistasRepositoryImpl
import org.lighthousegames.logging.logging

private val logger = logging()

class TenistaServiceimpl(
    private val repository: TenistaRepository = TenistasRepositoryImpl()
) : TenistaService {
    override fun getAll(): List<Tenista> {
        logger.debug { "Obteniendo todos los tenistas" }
        val tenistas = repository.findAll()
        logger.debug { "Se obtuvieron ${tenistas.size} tenistas" }
        return tenistas
    }

    override fun getById(id: Long): Tenista? {
        logger.debug { "Buscando tenista con id: $id" }
        val tenista = repository.findById(id)
        if (tenista != null) {
            logger.debug { "Se encontró el tenista con id: $id" }
        } else {
            logger.debug { "No se encontró el tenista con id: $id" }
        }
        return tenista
    }

    override fun create(tenista: Tenista): Tenista {
        logger.debug { "Guardando tenista: $tenista" }
        val savedTenista = repository.save(tenista)
        logger.debug { "Tenista guardado con éxito: $savedTenista" }
        return savedTenista
    }

    override fun update(id: Long, tenista: Tenista): Tenista? {
        logger.debug { "Actualizando tenista con id : $id" }
        val updatedTenista = repository.update(id, tenista)
        if (updatedTenista != null) {
            logger.debug { "Tenista actualizado con éxito: $updatedTenista" }
        } else {
            logger.debug { "No se pudo actualizar el tenista con id: $id" }
        }
        return updatedTenista
    }

    override fun delete(id: Long): Tenista? {
        logger.debug { "Borrando tenista con id: $id" }
        val deletedTenista = repository.delete(id)
        if (deletedTenista != null) {
            logger.debug { "Tenista borrado con éxito: $deletedTenista" }
        } else {
            logger.debug { "No se pudo borrar el tenista con id: $id" }
        }
        return deletedTenista
    }
}
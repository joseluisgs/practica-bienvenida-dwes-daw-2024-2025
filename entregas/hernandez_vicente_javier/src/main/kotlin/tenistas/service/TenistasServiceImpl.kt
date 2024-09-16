package tenistas.service

import com.github.michaelbull.result.*
import org.lighthousegames.logging.logging
import tenistas.cache.CacheTenistasImpl
import tenistas.errors.FileError
import tenistas.errors.TenistaError
import tenistas.models.Tenista
import tenistas.repositories.TenistasRepository
import tenistas.storage.TenistasStorage
import java.io.File
private val logger = logging()

/**
 * Servicio de tenistas que interactúa con el repositorio y el almacenamiento.
 * @param tenistasStorage Almacenamiento de tenistas.
 * @param tenistasRepository Repositorio de tenistas.
 * @param cache Caché de tenistas.
 * @author Javier Hernández
 * @since 1.0
 */
class TenistasServiceImpl(
    private val tenistasStorage: TenistasStorage,
    private val tenistasRepository: TenistasRepository,
    private val cache: CacheTenistasImpl
) : TenistasService {
    /**
     * Obtiene todos los tenistas.
     * @return Resultado con la lista de tenistas o un error.
     * @since 1.0
     * @author Javier Hernández
     */
    override fun getAllTenistas(): Result<List<Tenista>, TenistaError> {
        logger.debug { "Getting all tenistas" }
        return Ok(tenistasRepository.getAllTenistas())
    }

    /**
     * Obtiene un tenista por su id.
     * @param id Id del tenista.
     * @return Resultado con el tenista o un error.
     * @since 1.0
     * @author Javier Hernández
     */

    override fun getTenistaById(id: Long): Result<Tenista, TenistaError> {
        logger.debug { "Getting tenista by id: $id" }
        return cache.get(id).mapBoth(
            success = {
                logger.debug { "Tenista from cache: $it" }
                Ok(it)
            },
            failure = {
                logger.debug { "Estudiante no encontrado en la cache" }
                tenistasRepository.getTenistaById(id)
                    ?.let { Ok(it) }
                    ?: Err(TenistaError.TenistaNotFound("Tenista no encontrado con id: $id"))
            }
        )
    }

    /**
     * Obtiene un tenista por su nombre.
     * @param nombre Nombre del tenista.
     * @return Resultado con el tenista o un error.
     * @since 1.0
     * @author Javier Hernández
     */

    override fun getTenistaByNombre(nombre: String): Result<Tenista, TenistaError> {
        logger.debug { "Getting tenista by nombre: $nombre" }
        return tenistasRepository.getTenistaByName(nombre)
            ?.let { Ok(it) }
            ?: Err(TenistaError.TenistaNotFound("Tenista no encontrado con nombre: $nombre"))
    }

    /**
     * Almacena un tenista.
     * @param tenista Tenista a almacenar.
     * @return Resultado con el tenista almacenado o un error.
     * @since 1.0
     * @author Javier Hernández
     */
    override fun createTenista(tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Creating tenista: $tenista" }
        return Ok(tenistasRepository.saveTenista(tenista)).also { cache.put(tenista.id, tenista) }
    }

    /**
     * Actualiza un tenista.
     * @param tenista Tenista a actualizar.
     * @return Resultado con el tenista actualizado o un error.
     * @since 1.0
     * @author Javier Hernández
     */

    override fun updateTenista(tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Updating tenista: $tenista" }
        return tenistasRepository.updateTenista(tenista)
            .also { cache.put(tenista.id, tenista) }
            ?.let { Ok(it) }
            ?: Err(TenistaError.TenistaNotUpdated("No se encontró el tenista con id: ${tenista.id}"))
    }

    /**
     * Elimina un tenista por su id.
     * @param id Id del tenista.
     * @return Resultado con un valor unitario o un error.
     * @since 1.0
     * @author Javier Hernández
     */
    override fun deleteTenistaById(id: Long): Result<Unit, TenistaError> {
        logger.debug { "Deleting tenista by id: $id" }
        return tenistasRepository.deleteById(id)
            ?.let {
                cache.remove(id)
                Ok(Unit)
            }
            ?: Err(TenistaError.TenistaNotDeleted("No se puedo eliminar al tenista con id: $id"))

    }

    /**
     * Lee un archivo CSV y almacena los tenistas.
     * @param file Archivo CSV.
     * @return Resultado con la lista de tenistas o un error.
     * @since 1.0
     * @author Javier Hernández
     */
    override fun readCSV(file: File): Result<List<Tenista>, FileError> {
        logger.debug { "Reading CSV file: $file" }
        return tenistasStorage.readCsv(file).andThen {tenistas ->
            tenistas.forEach { p ->
                tenistasRepository.saveTenista(p)
                logger.debug { "Stored tenista: $p" }
            }
            Ok(tenistas)
        }
    }

    /**
     * Almacena los tenistas en un archivo csv.
     * @param file Archivo csv.
     * @param tenistas Lista de tenistas.
     * @return Resultado con un valor unitario o un error.
     * @since 1.0
     * @author Javier Hernández
     */
    override fun writeCSV(file: File, tenistas: List<Tenista>): Result<Unit, FileError> {
        logger.debug { "Writing CSV file: $file" }
        return tenistasStorage.storeCsv(file, tenistas)
    }

    /**
     * Alamacena los tenistas en un archivo JSON.
     * @param file Archivo JSON.
     * @param tenistas Lista de tenistas.
     * @return Resultado con un valor unitario o un error.
     * @since 1.0
     * @author Javier Hernández
     */

    override fun writeJson(file: File, tenistas: List<Tenista>): Result<Unit, FileError> {
        logger.debug { "Writing JSON file: $file" }
        return tenistasStorage.storeJson(file, tenistas)
    }

    /**
     * Almacena los tenistas en un archivo XML.
     * @param file Archivo XML.
     * @param tenistas Lista de tenistas.
     * @return Resultado con un valor unitario o un error.
     * @since 1.0
     * @author Javier Hernández
     */

    override fun writeXml(file: File, tenistas: List<Tenista>): Result<Unit, FileError> {
        logger.debug { "Writing XML file: $file" }
        return tenistasStorage.storeXml(file, tenistas)
    }

}
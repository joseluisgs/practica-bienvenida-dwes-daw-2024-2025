package config

import org.lighthousegames.logging.logging
import java.io.InputStream
import java.util.*

private val logger = logging()

private const val CONFIG_FILE_NAME = "config.properties"

/**
 * Objeto de configuración para la aplicación.
 *
 * @author Natalia González
 * @version 1.0
 */
object Config {

    /**
     * Indica si las tablas de la base de datos deben inicializarse.
     * Se puede configurar desde el archivo de propiedades con la clave `database.init.tables`.
     */
    var databaseInitTables: Boolean = true

    /**
     * Ruta de almacenamiento de datos.
     * Se puede configurar desde el archivo de propiedades con la clave `storage.data`.
     */
    var storageData: String = "data"

    /**
     * URL de la base de datos.
     * Se puede configurar desde el archivo de propiedades con la clave `database.url`.
     * Si no se encuentra, utiliza un valor por defecto `jdbc:sqlite:Database.db`.
     */
    val dataBaseUrl: String by lazy {
        readProperty("database.url") ?: "jdbc:sqlite:Database.db"
    }

    /**
     * Lee una propiedad del archivo de configuración `config.properties`.
     *
     * @param propiedad Nombre de la propiedad a leer.
     * @return El valor de la propiedad como [String], o `null` si ocurre un error o la propiedad no existe.
     */
    private fun readProperty(propiedad: String): String? {
        return try {
            logger.debug { "Leyendo propiedad: $propiedad" }
            val properties = Properties()
            val inputStream: InputStream = ClassLoader.getSystemResourceAsStream(CONFIG_FILE_NAME)
                ?: throw Exception("No se puede leer el fichero de configuración $CONFIG_FILE_NAME")
            properties.load(inputStream)

            databaseInitTables = properties.getProperty("database.init.tables", databaseInitTables.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", storageData)

            properties.getProperty(propiedad)
        } catch (e: Exception) {
            logger.error { "Error al leer la propiedad $propiedad: ${e.message}" }
            null
        }
    }
}
package config

import org.lighthousegames.logging.logging
import java.io.InputStream
import java.util.*

private val logger = logging()
private const val CONFIG_FILENAME = "config.properties"

object Config {

    private val actualDirectory = System.getProperty("user.dir")

    val dataBaseUrl: String by lazy {
        readProperty("database.url") ?: "jdbc:sqlite:tenista.db"
    }

    val dataBaseInit: Boolean by lazy {
        readProperty("dataBase.init")?.toBoolean() ?: true
    }

    val databaseInMemory: Boolean by lazy {
        readProperty("database.inmemory")?.toBoolean() ?: false
    }

    val storageDir : String by lazy {
        readProperty("storage.dir")?: "data"
    }

    val storageDirFileCsv: String by lazy {
        readProperty("storage.dirFileCsv") ?: "resources"
    }

    val storageFileCsv : String by lazy {
        readProperty("storage.fileCsv") ?: "data.csv"
    }

    val cacheSize: Int by lazy {
        readProperty("cache.size")?.toInt() ?: 5
    }

    val removeDatabase: Boolean by lazy {
        readProperty("database.removedata")?.toBoolean() ?: true
    }


    private fun readProperty(propiedad: String): String? {
        return try {
            logger.debug { "Leyendo propiedad: $propiedad" }
            val properties = Properties()
            val inputStream: InputStream = ClassLoader.getSystemResourceAsStream(CONFIG_FILENAME)
                ?: throw Exception("No se puede leer el fichero de configuración $CONFIG_FILENAME")
            properties.load(inputStream)
            properties.getProperty(propiedad)
        }catch (e: Exception){
            logger.error { "Error al leer la propiedad $propiedad: ${e.message}" }
            null
        }
    }

}
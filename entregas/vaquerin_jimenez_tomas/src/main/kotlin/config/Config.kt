package config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

private val logger = logging()

object Config {
    var databaseUrl: String = "jdbc:h2:./tenistas"
    var databaseInitTables: Boolean = false
    var databaseInitData: Boolean = false
    var storageData: String = "data"
    var cacheSize: Int = 5

    init {
        try {
            logger.debug { "Cargando configuración" }
            val properties = Properties()

            // Cargar el archivo config.properties desde el classpath
            val configStream = ClassLoader.getSystemResourceAsStream("config.properties")
            if (configStream == null) {
                logger.error { "No se pudo cargar el archivo config.properties" }
            } else {
                properties.load(configStream)

                // Leer las propiedades del archivo y sobrescribir los valores por defecto
                databaseUrl = properties.getProperty("database.url", databaseUrl)
                databaseInitTables =
                    properties.getProperty("database.init.tables", databaseInitTables.toString()).toBoolean()
                databaseInitData = properties.getProperty("database.init.data", databaseInitData.toString()).toBoolean()
                storageData = properties.getProperty("storage.data", storageData)
                cacheSize = properties.getProperty("cache.size", cacheSize.toString()).toInt()
                logger.debug { "Configuración cargada correctamente" }
            }

            // Crear el directorio de almacenamiento si no existe
            val storagePath = Path.of(storageData)
            if (Files.exists(storagePath)) {
                logger.debug { "El directorio $storageData ya existe" }
            } else {
                Files.createDirectories(storagePath)
                logger.debug { "Directorio $storageData creado" }
            }

        } catch (e: Exception) {
            logger.error(e) { "Error cargando configuración: ${e.message}" }
            logger.error { "Usando valores por defecto" }
        }
    }
}

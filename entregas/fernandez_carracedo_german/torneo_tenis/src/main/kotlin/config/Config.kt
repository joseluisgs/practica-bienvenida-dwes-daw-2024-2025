package org.example.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

private val logger = logging()

/**
 * Objeto Config donde cargaremos toda la configuracion
 * @property databaseUrl url de la base de datos
 * @property databaseInitTables true inicia tablas
 * @property databaseInitData true vacía los datos de las tablas
 * @property databaseInMemory true crea la base de datos en memoria
 * @property storageData direccion de almacenamiento de storage
 * @property cacheSize tamaño de la cache
 * @author German Fernandez
 * @since 1.0.0
 */
object Config {
    var databaseUrl: String = "jdbc:sqlite:tenistas.db"
        private set
    var databaseInitTables: Boolean = true
        private set
    var databaseInitData: Boolean = false
        private set
    var databaseInMemory: Boolean = false
        private set
    var storageData: String = "data"
        private set
    var cacheSize: Int = 5
        private set

    init {
        try {

            logger.debug { "Cargando configuración DataBaseManager" }
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables = properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData = properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
            databaseInMemory = properties.getProperty("database.inmemory", this.databaseInMemory.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", this.storageData)
            cacheSize = properties.getProperty("cache.size", this.cacheSize.toString()).toInt()
            logger.debug { "Configuración cargada correctamente" }

            // crear el directorio si no existe
            Files.createDirectories(Path(storageData))

        } catch (e: Exception) {
            logger.error { "Error cargando configuración: ${e.message}" }
            logger.error { "Usando valores por defecto" }
        }
    }
}
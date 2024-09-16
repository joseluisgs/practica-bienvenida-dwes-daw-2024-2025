package database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import config.Config
import org.example.database.Database
import org.lighthousegames.logging.logging

private val logger = logging()

/**
 * Clase que gestiona la conexión y las consultas de la base de datos utilizando SqlDelight.
 *
 * @property config Configuración de la aplicación que contiene información de la base de datos, como la URL.
 * @author Natalia González
 * @version 1.0
 */
class SqlDelightManager(
    private val config: Config
) {
    /**
     * Consultas de la base de datos generadas automáticamente por SqlDelight.
     * Se inicializan perezosamente al acceder por primera vez.
     */
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    // Inicializa las consultas y elimina todos los datos al crear la instancia.
    init {
        initQueries()
        removeAllData()
    }

    /**
     * Inicializa las consultas de la base de datos y crea las tablas necesarias.
     *
     * @return [DatabaseQueries] que permite realizar consultas a la base de datos.
     */
    fun initQueries(): DatabaseQueries {
        logger.debug { "SqlDeLightClient - File: ${config.dataBaseUrl}" }
        return JdbcSqliteDriver(config.dataBaseUrl).let { driver ->
            logger.debug { "Creando Tablas" }
            Database.Schema.create(driver)
            Database(driver)
        }.databaseQueries
    }

    /**
     * Elimina todos los datos de las tablas relevantes en la base de datos.
     * Se ejecuta en una transacción para garantizar la atomicidad.
     */
    private fun removeAllData() {
        databaseQueries.transaction {
            databaseQueries.deleteAllTenistas()
        }
    }
}
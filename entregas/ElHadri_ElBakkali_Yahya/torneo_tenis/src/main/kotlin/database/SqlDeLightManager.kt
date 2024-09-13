package database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.yahyaelhadri.database.AppDatabase
import org.lighthousegames.logging.logging

private val logger = logging()


class SqlDeLightManager(
    private val databaseUrl: String,
    private val databaseInMemory: Boolean,
    private val databaseInitData: Boolean,
) {
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        logger.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
        initialize()
    }

    private fun initQueries():DatabaseQueries {

        return if (databaseInMemory) {
            logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            logger.debug { "SqlDeLightClient - File: ${databaseUrl}" }
            JdbcSqliteDriver(databaseUrl)
        }.let { driver ->
            logger.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries

    }

    fun initialize() {
        if (databaseInitData) {
            removeAllData()
        }
    }


    private fun removeAllData() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.eliminarTodosTenistas()
        }
    }
}
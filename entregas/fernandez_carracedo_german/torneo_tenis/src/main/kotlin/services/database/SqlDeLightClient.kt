package org.example.services.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import org.example.config.Config
import org.example.database.AppDatabase
import org.lighthousegames.logging.logging

private val logger = logging()

object SqlDeLightClient {
    lateinit var databaseQueries: DatabaseQueries

    init {
        logger.debug { "Inicializando gestor de BBDD con SQLDelight" }
        initConfig()
    }

    private fun initConfig() {

        databaseQueries = if (Config.databaseInMemory) {
            logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            logger.debug { "SqlDeLightClient - File: ${Config.databaseUrl}" }
            JdbcSqliteDriver(Config.databaseUrl)
        }.let { driver ->
            // Creamos la base de datos
            logger.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries


        // Inicializamos datos de ejemplo
        initialize()


    }

    private fun initialize() {
        if (Config.databaseInitData) {
            removeAllData()
        }
    }

    private fun removeAllData() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction { databaseQueries.removeAllTenistas() }
    }

}
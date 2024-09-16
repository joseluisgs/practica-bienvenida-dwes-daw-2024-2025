package database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.example.database.service.Database
import org.lighthousegames.logging.logging


private val logger = logging()



class SqlDeLightManager(
    private val databaseUrl: String = "jdbc:sqlite:torneo.db"
) {
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        logger.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
    }

    private fun initQueries(): DatabaseQueries {
        return JdbcSqliteDriver(databaseUrl).let { driver ->
            logger.debug { "Borrando tablas si existen" }
            driver.execute(null, "DROP TABLE IF EXISTS TenistaEntity", 0)
            logger.debug { "Creando tablas" }
            Database.Schema.create(driver)
            Database(driver)
        }.databaseQueries
    }


}



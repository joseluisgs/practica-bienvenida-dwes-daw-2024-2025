package dev.javierhvicente.database.torneotenis

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import database.DatabaseQueries
import dev.javierhvicente.database.AppDatabase
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), AppDatabase {
  override val databaseQueries: DatabaseQueries = DatabaseQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS Tenistas_Entity (
          |id INTEGER PRIMARY KEY AUTOINCREMENT,
          |nombre TEXT NOT NULL,
          |pais TEXT NOT NULL,
          |altura INTEGER NOT NULL,
          |peso INTEGER NOT NULL,
          |puntos INTEGER NOT NULL,
          |mano TEXT NOT NULL,
          |fecha_nacimiento TEXT NOT NULL,
          |created_at TEXT NOT NULL,
          |upadated_at TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}

package dev.javierhvicente.database

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import database.DatabaseQueries
import dev.javierhvicente.database.torneotenis.newInstance
import dev.javierhvicente.database.torneotenis.schema
import kotlin.Unit

public interface AppDatabase : Transacter {
  public val databaseQueries: DatabaseQueries

  public companion object {
    public val Schema: SqlSchema<QueryResult.Value<Unit>>
      get() = AppDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): AppDatabase =
        AppDatabase::class.newInstance(driver)
  }
}

package database

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class DatabaseQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectAllTenistas(mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fechaNacimiento: String?,
    created_at: String,
    updated_at: String,
  ) -> T): Query<T> = Query(-475_284_228, arrayOf("TenistaEntity"), driver, "Database.sq",
      "selectAllTenistas",
      "SELECT TenistaEntity.id, TenistaEntity.nombre, TenistaEntity.pais, TenistaEntity.altura, TenistaEntity.peso, TenistaEntity.puntos, TenistaEntity.mano, TenistaEntity.fechaNacimiento, TenistaEntity.created_at, TenistaEntity.updated_at FROM TenistaEntity") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectAllTenistas(): Query<TenistaEntity> = selectAllTenistas { id, nombre, pais,
      altura, peso, puntos, mano, fechaNacimiento, created_at, updated_at ->
    TenistaEntity(
      id,
      nombre,
      pais,
      altura,
      peso,
      puntos,
      mano,
      fechaNacimiento,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> selectTenistaByNombre(nombre: String, mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fechaNacimiento: String?,
    created_at: String,
    updated_at: String,
  ) -> T): Query<T> = SelectTenistaByNombreQuery(nombre) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectTenistaByNombre(nombre: String): Query<TenistaEntity> =
      selectTenistaByNombre(nombre) { id, nombre_, pais, altura, peso, puntos, mano,
      fechaNacimiento, created_at, updated_at ->
    TenistaEntity(
      id,
      nombre_,
      pais,
      altura,
      peso,
      puntos,
      mano,
      fechaNacimiento,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> selectAllTenistasByPais(pais: String, mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fechaNacimiento: String?,
    created_at: String,
    updated_at: String,
  ) -> T): Query<T> = SelectAllTenistasByPaisQuery(pais) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectAllTenistasByPais(pais: String): Query<TenistaEntity> =
      selectAllTenistasByPais(pais) { id, nombre, pais_, altura, peso, puntos, mano,
      fechaNacimiento, created_at, updated_at ->
    TenistaEntity(
      id,
      nombre,
      pais_,
      altura,
      peso,
      puntos,
      mano,
      fechaNacimiento,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> selectTenistaByRanking(`value`: Long, mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fechaNacimiento: String?,
    created_at: String,
    updated_at: String,
  ) -> T): Query<T> = SelectTenistaByRankingQuery(value) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectTenistaByRanking(value_: Long): Query<TenistaEntity> =
      selectTenistaByRanking(value_) { id, nombre, pais, altura, peso, puntos, mano,
      fechaNacimiento, created_at, updated_at ->
    TenistaEntity(
      id,
      nombre,
      pais,
      altura,
      peso,
      puntos,
      mano,
      fechaNacimiento,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> selectTenistaById(id: Long, mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fechaNacimiento: String?,
    created_at: String,
    updated_at: String,
  ) -> T): Query<T> = SelectTenistaByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectTenistaById(id: Long): Query<TenistaEntity> = selectTenistaById(id) { id_,
      nombre, pais, altura, peso, puntos, mano, fechaNacimiento, created_at, updated_at ->
    TenistaEntity(
      id_,
      nombre,
      pais,
      altura,
      peso,
      puntos,
      mano,
      fechaNacimiento,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> selectTenistaEntityLastInserted(mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fechaNacimiento: String?,
    created_at: String,
    updated_at: String,
  ) -> T): Query<T> = Query(1_228_033_569, arrayOf("TenistaEntity"), driver, "Database.sq",
      "selectTenistaEntityLastInserted",
      "SELECT TenistaEntity.id, TenistaEntity.nombre, TenistaEntity.pais, TenistaEntity.altura, TenistaEntity.peso, TenistaEntity.puntos, TenistaEntity.mano, TenistaEntity.fechaNacimiento, TenistaEntity.created_at, TenistaEntity.updated_at FROM TenistaEntity WHERE id = last_insert_rowid()") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7),
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectTenistaEntityLastInserted(): Query<TenistaEntity> =
      selectTenistaEntityLastInserted { id, nombre, pais, altura, peso, puntos, mano,
      fechaNacimiento, created_at, updated_at ->
    TenistaEntity(
      id,
      nombre,
      pais,
      altura,
      peso,
      puntos,
      mano,
      fechaNacimiento,
      created_at,
      updated_at
    )
  }

  public fun removeAllTenistas() {
    driver.execute(-2_624_396, """DELETE FROM TenistaEntity""", 0)
    notifyQueries(-2_624_396) { emit ->
      emit("TenistaEntity")
    }
  }

  public fun insertTenista(
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fechaNacimiento: String?,
    created_at: String,
    updated_at: String,
  ) {
    driver.execute(1_054_068_819,
        """INSERT INTO TenistaEntity (nombre, pais, altura, peso, puntos, mano, fechaNacimiento, created_at, updated_at) VALUES (?, ?, ?, ?, ?,?,?,?,?)""",
        9) {
          bindString(0, nombre)
          bindString(1, pais)
          bindLong(2, altura)
          bindLong(3, peso)
          bindLong(4, puntos)
          bindString(5, mano)
          bindString(6, fechaNacimiento)
          bindString(7, created_at)
          bindString(8, updated_at)
        }
    notifyQueries(1_054_068_819) { emit ->
      emit("TenistaEntity")
    }
  }

  public fun updateTenista(
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fechaNacimiento: String?,
    updated_at: String,
    id: Long,
  ) {
    driver.execute(69_625_923,
        """UPDATE TenistaEntity SET nombre = ?, pais = ?, altura = ?, peso=?, puntos=?, mano=?, fechaNacimiento=?, updated_at = ? WHERE id = ?""",
        9) {
          bindString(0, nombre)
          bindString(1, pais)
          bindLong(2, altura)
          bindLong(3, peso)
          bindLong(4, puntos)
          bindString(5, mano)
          bindString(6, fechaNacimiento)
          bindString(7, updated_at)
          bindLong(8, id)
        }
    notifyQueries(69_625_923) { emit ->
      emit("TenistaEntity")
    }
  }

  public fun deleteTenista(id: Long) {
    driver.execute(1_360_804_129, """DELETE FROM TenistaEntity WHERE id = ?""", 1) {
          bindLong(0, id)
        }
    notifyQueries(1_360_804_129) { emit ->
      emit("TenistaEntity")
    }
  }

  private inner class SelectTenistaByNombreQuery<out T : Any>(
    public val nombre: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("TenistaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("TenistaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_138_164_784,
        """SELECT TenistaEntity.id, TenistaEntity.nombre, TenistaEntity.pais, TenistaEntity.altura, TenistaEntity.peso, TenistaEntity.puntos, TenistaEntity.mano, TenistaEntity.fechaNacimiento, TenistaEntity.created_at, TenistaEntity.updated_at FROM TenistaEntity WHERE nombre = ?""",
        mapper, 1) {
      bindString(0, nombre)
    }

    override fun toString(): String = "Database.sq:selectTenistaByNombre"
  }

  private inner class SelectAllTenistasByPaisQuery<out T : Any>(
    public val pais: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("TenistaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("TenistaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-721_429_522,
        """SELECT TenistaEntity.id, TenistaEntity.nombre, TenistaEntity.pais, TenistaEntity.altura, TenistaEntity.peso, TenistaEntity.puntos, TenistaEntity.mano, TenistaEntity.fechaNacimiento, TenistaEntity.created_at, TenistaEntity.updated_at FROM TenistaEntity WHERE pais = ?""",
        mapper, 1) {
      bindString(0, pais)
    }

    override fun toString(): String = "Database.sq:selectAllTenistasByPais"
  }

  private inner class SelectTenistaByRankingQuery<out T : Any>(
    public val `value`: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("TenistaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("TenistaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-2_067_947_249, """
    |SELECT TenistaEntity.id, TenistaEntity.nombre, TenistaEntity.pais, TenistaEntity.altura, TenistaEntity.peso, TenistaEntity.puntos, TenistaEntity.mano, TenistaEntity.fechaNacimiento, TenistaEntity.created_at, TenistaEntity.updated_at FROM TenistaEntity
    |ORDER BY puntos DESC
    |LIMIT 1 OFFSET ? - 1
    """.trimMargin(), mapper, 1) {
      bindLong(0, value)
    }

    override fun toString(): String = "Database.sq:selectTenistaByRanking"
  }

  private inner class SelectTenistaByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("TenistaEntity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("TenistaEntity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-554_576_734,
        """SELECT TenistaEntity.id, TenistaEntity.nombre, TenistaEntity.pais, TenistaEntity.altura, TenistaEntity.peso, TenistaEntity.puntos, TenistaEntity.mano, TenistaEntity.fechaNacimiento, TenistaEntity.created_at, TenistaEntity.updated_at FROM TenistaEntity WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "Database.sq:selectTenistaById"
  }
}

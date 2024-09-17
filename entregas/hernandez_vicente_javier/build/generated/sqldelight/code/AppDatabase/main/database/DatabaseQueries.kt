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
  public fun <T : Any> selectAll(mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fecha_nacimiento: String,
    created_at: String,
    upadated_at: String,
  ) -> T): Query<T> = Query(-1_396_145_731, arrayOf("Tenistas_Entity"), driver, "database.sq",
      "selectAll",
      "SELECT Tenistas_Entity.id, Tenistas_Entity.nombre, Tenistas_Entity.pais, Tenistas_Entity.altura, Tenistas_Entity.peso, Tenistas_Entity.puntos, Tenistas_Entity.mano, Tenistas_Entity.fecha_nacimiento, Tenistas_Entity.created_at, Tenistas_Entity.upadated_at FROM Tenistas_Entity") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectAll(): Query<Tenistas_Entity> = selectAll { id, nombre, pais, altura, peso,
      puntos, mano, fecha_nacimiento, created_at, upadated_at ->
    Tenistas_Entity(
      id,
      nombre,
      pais,
      altura,
      peso,
      puntos,
      mano,
      fecha_nacimiento,
      created_at,
      upadated_at
    )
  }

  public fun <T : Any> selectByName(nombre: String, mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fecha_nacimiento: String,
    created_at: String,
    upadated_at: String,
  ) -> T): Query<T> = SelectByNameQuery(nombre) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectByName(nombre: String): Query<Tenistas_Entity> = selectByName(nombre) { id,
      nombre_, pais, altura, peso, puntos, mano, fecha_nacimiento, created_at, upadated_at ->
    Tenistas_Entity(
      id,
      nombre_,
      pais,
      altura,
      peso,
      puntos,
      mano,
      fecha_nacimiento,
      created_at,
      upadated_at
    )
  }

  public fun <T : Any> selectById(id: Long, mapper: (
    id: Long,
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fecha_nacimiento: String,
    created_at: String,
    upadated_at: String,
  ) -> T): Query<T> = SelectByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!,
      cursor.getString(9)!!
    )
  }

  public fun selectById(id: Long): Query<Tenistas_Entity> = selectById(id) { id_, nombre, pais,
      altura, peso, puntos, mano, fecha_nacimiento, created_at, upadated_at ->
    Tenistas_Entity(
      id_,
      nombre,
      pais,
      altura,
      peso,
      puntos,
      mano,
      fecha_nacimiento,
      created_at,
      upadated_at
    )
  }

  public fun updateTenista(
    nombre: String,
    pais: String,
    altura: Long,
    peso: Long,
    puntos: Long,
    mano: String,
    fecha_nacimiento: String,
    upadated_at: String,
    id: Long,
  ) {
    driver.execute(-1_976_563_613,
        """UPDATE Tenistas_Entity SET nombre = ? , pais = ?, altura = ?,peso = ?, puntos = ?, mano = ?, fecha_nacimiento = ?, upadated_at = ? WHERE id = ?""",
        9) {
          bindString(0, nombre)
          bindString(1, pais)
          bindLong(2, altura)
          bindLong(3, peso)
          bindLong(4, puntos)
          bindString(5, mano)
          bindString(6, fecha_nacimiento)
          bindString(7, upadated_at)
          bindLong(8, id)
        }
    notifyQueries(-1_976_563_613) { emit ->
      emit("Tenistas_Entity")
    }
  }

  public fun deleteByName(nombre: String) {
    driver.execute(1_419_737_941, """DELETE FROM Tenistas_Entity WHERE nombre = ?""", 1) {
          bindString(0, nombre)
        }
    notifyQueries(1_419_737_941) { emit ->
      emit("Tenistas_Entity")
    }
  }

  public fun deleteById(id: Long) {
    driver.execute(-1_205_225_371, """DELETE FROM Tenistas_Entity WHERE id = ?""", 1) {
          bindLong(0, id)
        }
    notifyQueries(-1_205_225_371) { emit ->
      emit("Tenistas_Entity")
    }
  }

  public fun deleteAllTenistas() {
    driver.execute(-1_224_799_987, """DELETE FROM Tenistas_Entity""", 0)
    notifyQueries(-1_224_799_987) { emit ->
      emit("Tenistas_Entity")
    }
  }

  public fun insertTenista(
    nombre: String,
    pais: String,
    altura: Long,
    puntos: Long,
    peso: Long,
    mano: String,
    fecha_nacimiento: String,
    created_at: String,
    upadated_at: String,
  ) {
    driver.execute(-992_120_717,
        """INSERT INTO Tenistas_Entity (nombre, pais, altura, puntos, peso, mano, fecha_nacimiento, created_at, upadated_at) VALUES (?,?,?,?,?,?,?,?, ?)""",
        9) {
          bindString(0, nombre)
          bindString(1, pais)
          bindLong(2, altura)
          bindLong(3, puntos)
          bindLong(4, peso)
          bindString(5, mano)
          bindString(6, fecha_nacimiento)
          bindString(7, created_at)
          bindString(8, upadated_at)
        }
    notifyQueries(-992_120_717) { emit ->
      emit("Tenistas_Entity")
    }
  }

  private inner class SelectByNameQuery<out T : Any>(
    public val nombre: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Tenistas_Entity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Tenistas_Entity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-74_339_866,
        """SELECT Tenistas_Entity.id, Tenistas_Entity.nombre, Tenistas_Entity.pais, Tenistas_Entity.altura, Tenistas_Entity.peso, Tenistas_Entity.puntos, Tenistas_Entity.mano, Tenistas_Entity.fecha_nacimiento, Tenistas_Entity.created_at, Tenistas_Entity.upadated_at FROM Tenistas_Entity WHERE nombre = ?""",
        mapper, 1) {
      bindString(0, nombre)
    }

    override fun toString(): String = "database.sq:selectByName"
  }

  private inner class SelectByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Tenistas_Entity", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Tenistas_Entity", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-330_803_402,
        """SELECT Tenistas_Entity.id, Tenistas_Entity.nombre, Tenistas_Entity.pais, Tenistas_Entity.altura, Tenistas_Entity.peso, Tenistas_Entity.puntos, Tenistas_Entity.mano, Tenistas_Entity.fecha_nacimiento, Tenistas_Entity.created_at, Tenistas_Entity.upadated_at FROM Tenistas_Entity WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "database.sq:selectById"
  }
}

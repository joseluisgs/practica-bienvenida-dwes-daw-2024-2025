import java.sql.Connection
import java.sql.Statement

fun createTable(conn: Connection) {
    val sql = """
        CREATE TABLE IF NOT EXISTS tenistas (
            id INTEGER PRIMARY KEY ,
            nombre TEXT NOT NULL,
            pais TEXT NOT NULL,
            altura INTEGER NOT NULL,
            peso INTEGER NOT NULL,
            puntos INTEGER NOT NULL,
            mano TEXT CHECK( mano IN ('DIESTRO', 'ZURDO') ) NOT NULL,
            fecha_nacimiento TEXT NOT NULL,
            created_at TEXT DEFAULT CURRENT_TIMESTAMP,
            updated_at TEXT DEFAULT CURRENT_TIMESTAMP
        );
    """.trimIndent()

    val deleteSQL = "DELETE FROM tenistas;"

    try {
        val stmt: Statement = conn.createStatement()
        stmt.execute(sql)
        println("Tabla 'tenistas' creada o ya existe")
        //vaciar la tabla
        stmt.execute(deleteSQL)
        println("Tabla 'tenistas' vaciada")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
fun insertTenista(conn: Connection, tenista: Map<String, String>) {
    //crea una consulta sql para insertar un tenista
    val sql = """
        INSERT INTO tenistas(nombre, pais, altura, peso, puntos, mano, fecha_nacimiento, created_at, updated_at) 
        VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    """.trimIndent()
//inserta un tenista
    try {
        val pstmt = conn.prepareStatement(sql)
        pstmt.setString(1, tenista["nombre"])
        pstmt.setString(2, tenista["pais"])
        pstmt.setInt(3, tenista["altura"]?.toInt() ?: 0)
        pstmt.setInt(4, tenista["peso"]?.toInt() ?: 0)
        pstmt.setInt(5, tenista["puntos"]?.toInt() ?: 0)
        pstmt.setString(6, tenista["mano"])
        pstmt.setString(7, tenista["fecha_nacimiento"])

        pstmt.executeUpdate()
        println("Tenista insertado: ${tenista["nombre"]}")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun obtenerTenistas(conn: Connection): List<Tenista> {
    val tenistas = mutableListOf<Tenista>()
    val query = "select id, nombre, pais, altura, peso, puntos, mano, fecha_nacimiento from tenistas"

    conn.createStatement().use { stmt ->
        val resultSet = stmt.executeQuery(query)
        while (resultSet.next()) {
            tenistas.add(
                Tenista(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("pais"),
                    resultSet.getInt("altura"),
                    resultSet.getInt("peso"),
                    resultSet.getInt("puntos"),
                    resultSet.getString("mano"),
                    resultSet.getString("fecha_nacimiento")
                ))
        }

    }
    return tenistas
}
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import com.google.gson.Gson
import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import org.simpleframework.xml.core.Persister
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {

    //comprobar que tenga argumenetos
    if (args.isEmpty()) {
        println("Debe de Pasar como minimo un Parametro")
        return
    }
    //comprobar que no tenga mas de 2 argumetos
    if (args.size > 2) {
        println("Los argumentos no pueden ser más de 2")
        return
    }
//comprobar que el fichero de entrada es .csv
    if (!args[0].endsWith(".csv")) {
        println("Debe de Pasar un archivo .csv")
        return
    }
//comprobar que el fichero de salida por defecto es .json
    var ficheroSalida = "torneo_tenis.json"
    var sufijo = "json"
//comprobar que el fichero de salida es .json, .csv o .xml
    if (args.size == 2) {
        ficheroSalida = args[1]
        if (!args[1].endsWith(".json") && !args[1].endsWith(".csv") && !args[1].endsWith(".xml")) {
            println("Debe de Pasar un archivo .json, .csv o .xml")
            return
        } else {
            sufijo = args[1].substringAfterLast(".")
        }
    }


//validar ruta fichero salida
    var rutaSalida = File(ficheroSalida)
    val directorioPadre = rutaSalida.parentFile
    if (directorioPadre != null) {
        if (!directorioPadre.exists()) {
            println("El directorio padre no existe: ${directorioPadre.absolutePath}")
            return
        } else if (!directorioPadre.canWrite()) {
            println("No tiene permisos para escribir en el directorio: ${directorioPadre.absolutePath}")
            return
        }
    }
//validar ruta fichero entrada
    val ficheroEntrada = File(args[0])
    if (!ficheroEntrada.parentFile.exists() || !ficheroEntrada.canRead()) {
        println("El archivo de entrada no existe o no se puede leer: ${ficheroEntrada.absolutePath}")
        return
    }

    // Conectarse a la base de datos SQLite
    val conn = connectSQLite()
    // Crear la tabla 'tenistas' si no existe
    conn?.let {
        createTable(it)
        // Cargar los datos del archivo CSV en la tabla 'tenistas'
        val tenistas = readCSVFile(ficheroEntrada)
        tenistas.forEach { tenista ->
            insertTenista(it, tenista)  // Inserta cada tenista en la base de datos
        }

        // Consultar y exportar los datos de la tabla 'tenistas'
        consultaTenistas(obtenerTenistas(it))
        if (sufijo == "json") {
            exportarTenistasAJson(obtenerTenistas(it), ficheroSalida)
        }
        if (sufijo == "csv") {
            exportarTenistasACsv(obtenerTenistas(it), ficheroSalida)
        }

        if (sufijo == "xml") {
            exportarTenistasAxml(obtenerTenistas(it), ficheroSalida)
        }

        it.close()  // Cierra la conexión después de usarla
    }


}


fun connectSQLite(): Connection? {
    var conn: Connection? = null
    try {
        val url = "jdbc:sqlite:tenistas.db" // Nombre del archivo de base de datos
        conn = DriverManager.getConnection(url)
        println("Conexión establecida con SQLite")
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return conn
}
// Funcion para leer los datos del archivo CSV con encabezado que devuelve una lista de mapas
fun readCSVFile(file: File): List<Map<String, String>> {
    val tenistas = mutableListOf<Map<String, String>>()

    try {
        CSVReader(FileReader(file)).use { reader ->
            val headers = reader.readNext() ?: throw IllegalArgumentException("Archivo CSV vacío o sin encabezado")
            var row: Array<String>?

            while (reader.readNext().also { row = it } != null) {
                val rowMap = headers.zip(row!!.toList()).toMap() // Convierte la fila a un mapa encabezado -> valor

                if (validateRow(rowMap)) {
                    tenistas.add(rowMap)
                } else {
                    println("Fila inválida: $rowMap")
                }
            }
        }
    } catch (e: Exception) {
        println("Error al leer el archivo CSV: ${e.message}")
    }
    return tenistas
}

//verifica que las filas tengan todos los campos (id, nombre, pais, altura, peso, puntos, mano, fecha_nacimiento)
fun validateRow(row: Map<String, String>): Boolean {
    try {
        val nombre = row["nombre"] ?: return false
        val pais = row["pais"] ?: return false
        val altura = row["altura"]?.toIntOrNull() ?: return false
        val peso = row["peso"]?.toIntOrNull() ?: return false
        val puntos = row["puntos"]?.toIntOrNull() ?: return false
        val mano = row["mano"] ?: return false
        if (mano != "DIESTRO" && mano != "ZURDO") return false
        val fechaNacimiento = row["fecha_nacimiento"]
        return true
    } catch (e: Exception) {
        return false
    }
}
fun consultaTenistas(tenistas: List<Tenista>) {
    //ordenarado por puntos
    val tenistasRankingMayorAMenor = tenistas.sortedByDescending { it.puntos }
    print("\ntenistas ordenados por puntos\n")
    tenistasRankingMayorAMenor.forEach { tenista -> println(tenista.nombre + ":" + tenista.puntos) }

    //Media de altura de los tenistas
    val mediaAltura = tenistas.sumOf { it.altura } / tenistas.size
    println("\nLa media de altura de los tenistas \n")
    println("La media de altura es: $mediaAltura")

    //media de peso de los tenistas
    val mediaPeso = tenistas.sumOf { it.peso } / tenistas.size
    println("\nmedia de peso de los tenistas \n")
    println("La media de peso es: $mediaPeso")

    //tenista mas alto
    val tenistaMasAlto = tenistas.maxByOrNull { it.altura }
    println("\ntenista mas alto \n")
    println("El tenista mas alto es: ${tenistaMasAlto?.nombre}")

    //tenistas de españa
    val tenistasEspana = tenistas.filter { it.pais == "España" }
    println("\ntenistas de españa \n")
    println("Los tenistas de españa son: ${tenistasEspana.map { it.nombre }}")

    //tenistas agrupados por pais
    val tenistasPais = tenistas.groupBy { it.pais }
    println("\ntenistas agrupados por pais \n")
    tenistasPais.forEach { (pais, tenistas) ->
        println("Los tenistas de $pais son: ${tenistas.map { it.nombre }}")
    }

    //tenistasagrupados por país y ordenados por puntos descendientes
    println("\ntenistas agrupados por pais y ordenados por puntos descendientes \n")
    val tenistasPaisOrdenadoPuntos = tenistasPais.mapValues { it.value.sortedByDescending { it.puntos } }
    tenistasPaisOrdenadoPuntos.forEach { (pais, tenistas) ->
        println("Los tenistas de $pais son: ${tenistas.map { it.nombre }}")
    }

    //tenistas agrupados por mano dominante y puntuación media de ellos
    println("\ntenistas agrupados por mano dominante y puntuación media de ellos \n")
    val tenistasAgrupadosMano = tenistas.groupBy { it.mano }
    tenistasAgrupadosMano.forEach { (mano, tenistas) ->
        val puntuacionMedia = tenistas.map { it.puntos }.average()
        println("Los tenistas de $mano tienen una puntuación media de $puntuacionMedia")
    }

    //puntuación total de los tenistas agrupados por país
    println("\npuntuación total de los tenistas agrupados por pais \n")
    val puntuacionTotalPorPais = tenistas.groupBy { it.pais }.mapValues { it.value.sumOf { it.puntos } }
    puntuacionTotalPorPais.forEach { (pais, puntuacionTotal) ->
        println("La puntuación total de los tenistas de $pais es $puntuacionTotal")
    }

    //país con más puntuación total
    println("\npaís con más puntuación total \n")
    val paisMasPuntuacion = puntuacionTotalPorPais.maxByOrNull { it.value }
    println("El país con más puntuación total es ${paisMasPuntuacion?.key}")

    //tenista con mejor ranking de españa
    println("\ntenista con mejor ranking de españa \n")
    val tenistaMejorRankingEspana = tenistasEspana.maxByOrNull { it.puntos }
    println("El tenista con mejor ranking de españa es ${tenistaMejorRankingEspana?.nombre}")
}

fun exportarTenistasAJson(tenistas: List<Tenista>, ficheroSalida: String) {
    val gson = Gson()
    val jsonString = gson.toJson(tenistas)
    File(ficheroSalida).writeText(jsonString)
    println("Tenistas exportados a $ficheroSalida")
}


fun exportarTenistasACsv(tenistas: List<Tenista>, ficheroSalida: String) {
    try {
        CSVWriter(FileWriter(File(ficheroSalida))).use { writer ->
            // Escribir los encabezados
            val encabezados = arrayOf("id", "nombre", "pais", "altura", "peso", "puntos", "mano", "fecha_nacimiento")
            writer.writeNext(encabezados)

            // Escribir los datos de los tenistas
            tenistas.forEach { tenista ->
                val datos = arrayOf(
                    tenista.id.toString(),
                    tenista.nombre,
                    tenista.pais,
                    tenista.altura.toString(),
                    tenista.peso.toString(),
                    tenista.puntos.toString(),
                    tenista.mano,
                    tenista.fechaNacimiento
                )
                writer.writeNext(datos)
            }
        }
        println("Tenistas exportados a $ficheroSalida")
    } catch (e: Exception) {
        println("Error al exportar tenistas a CSV: ${e.message}")
    }
}

fun exportarTenistasAxml(tenistas: List<Tenista>, ficheroSalida: String) {
    // Crea un objeto de la clase que contiene la lista de tenistas
    val tenistasWrapper = TenistasWrapper(tenistas)

    // Crea el serializer de Simple Framework
    val serializer = Persister()

    // Serializa la lista a XML y guarda en el archivo
    val file = File(ficheroSalida)
    serializer.write(tenistasWrapper, file)

    println("Tenistas exportados a $ficheroSalida")
}

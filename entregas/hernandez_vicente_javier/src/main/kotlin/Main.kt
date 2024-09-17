import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.Companion.rgb
import com.github.ajalt.mordant.terminal.Terminal
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.mapBoth
import config.Config
import database.SqlDelightManager
import org.lighthousegames.logging.logging
import tenistas.cache.CacheTenistasImpl
import tenistas.errors.ArgsErrors
import tenistas.errors.CsvErrors
import tenistas.repositories.TenistasRepositoryImpl
import tenistas.service.TenistasServiceImpl
import tenistas.storage.TenistasStorageImpl
import tenistas.validators.validateArgsEntrada
import tenistas.validators.validateCsvFormat
import java.io.File

private val logger = logging()
private val terminal = Terminal()
fun main(args: Array<String>) {
    if(args.isEmpty()) {
        println("No arguments provided.")
    }
    validateArgsEntrada(args[0]).mapBoth(
        success = { println("Archivo válido: $it") },
        failure = {
            Err(ArgsErrors.InvalidArgumentsError("Error: El argumento introducido no es válido"))
        }
    )
    validateCsvFormat(args[0]).mapBoth(
        success = { println("Formato CSV válido: $it") },
        failure = {
            Err(CsvErrors.InvalidCsvFormat("Error: El formato del archivo no es CSV"))
        }
    )

    val tenistasService = TenistasServiceImpl(
        tenistasStorage = TenistasStorageImpl(),
        tenistasRepository = TenistasRepositoryImpl(SqlDelightManager(Config)),
        cache = CacheTenistasImpl(Config.cacheSize)
    )
    tenistasService.readCSV(File(args[0])).mapBoth(
        success = { println("CSV leído correctamente") },
        failure = {
            Err(CsvErrors.InvalidCsvFormat("Error: No se ha podido leer el archivo CSV"))
        }
    )

    val listaTenistas = tenistasService.getAllTenistas().value

    terminal.println(rgb("#08ff00")("Consultas de los tenistas: 🎾\n"))
    terminal.println(TextColors.blue("Tenistas ordenados por ranking\n"))
    val ranking = listaTenistas.sortedByDescending { it.puntos }
    ranking.forEach { println("${it.nombre} - ${it.puntos} pts") }

    terminal.println(TextColors.blue("Media de altura de los tenistas\n"))
    println("${listaTenistas.map { it.altura}.average() } cm")

    terminal.println(TextColors.blue("Media de peso de los tenistas\n"))
    println("${listaTenistas.map { it.peso }.average()} kg")

    terminal.println(TextColors.blue("Tenista más alto\n"))
    val tenistaMasAlto = listaTenistas.maxBy { it.altura }
    println("Tenista: ${tenistaMasAlto.nombre}, Altura: ${tenistaMasAlto.altura} cm")

    terminal.println(TextColors.blue("Tenistas de España\n"))
    listaTenistas.filter { it.pais == "España" }.forEach { println("${it.nombre}") }

    terminal.println(TextColors.blue("Tenistas agrupados por país\n"))
    listaTenistas.groupBy { it.pais }.forEach{pais, tenistas ->
        println("País: $pais")
        tenistas.forEach { println("${it.nombre}\n") }
    }

    terminal.println(TextColors.blue("Número de tenistas agrupados por país y ordenados por puntos descendiente\n"))
    listaTenistas.groupBy { it.pais }
        .mapValues { tenistas ->
            tenistas.value.sortedByDescending { it.puntos }
        }.forEach { pais, tenistas ->
            println("País: $pais")
            tenistas.forEach { println("${it.nombre} - ${it.puntos} pts\n") }
        }

    terminal.println(TextColors.blue("Número de tenistas agrupados por mano dominante y puntuación media de ellos\n"))
    listaTenistas.groupBy { it.mano }.forEach { mano, tenista ->
        val puntosMedia = String.format("%.2f", tenista.map { it.puntos }.average())
        println("Mano: $mano - Número de tenistas: -Puntos media: $puntosMedia")
    }

    terminal.println(TextColors.blue("Puntuación total de los tenistas agrupados por país\n"))
    listaTenistas.groupBy { it.pais }.forEach { pais, tenistas ->
        val puntosTotales = tenistas.map { it.puntos }.sum()
        println("País: $pais - Puntos totales: $puntosTotales\n")
    }

    terminal.println(TextColors.blue("País con mayor puntuación total\n"))
    val pais = listaTenistas.groupBy { it.pais }.maxByOrNull { it.value.map { it.puntos }.sum() }
    println("País: ${pais?.key}")
    println(" - Puntuación total: ${pais?.value?.map { it.puntos }?.sum()}\n")

    terminal.println(TextColors.blue("Tenista con mejor ranking de España\n"))
    val tenista = listaTenistas.filter { it.pais == "España" }.maxByOrNull { it.puntos }
    println("${tenista?.nombre} - Puntos ${tenista?.puntos}\n")

    if(args.size > 1) {
        when{
            args[1].contains(".json") -> {
                tenistasService.writeJson(File(args[1]), ranking)
            }
            args[1].contains(".xml") -> {
                tenistasService.writeXml(File(args[1]), ranking)
            }
            args[1].contains(".csv") -> {
                tenistasService.writeCSV(File(args[1]), ranking)
            }
            else -> {
                tenistasService.writeJson(File(args[1]), ranking)
            }
        }
    }else{
        tenistasService.writeJson(File("torneo_tenis.json"), ranking)
    }

    val ficheroSalida = if(args.size == 2) {
        args[1]
    } else {
        "torneo_tenis.json"
    }

    val archivo = if (File(ficheroSalida).isAbsolute) {
        File(ficheroSalida)
    } else {
        File(System.getProperty("user.dir"), ficheroSalida)
    }
    archivo.parentFile?.let { parentDir ->
        if (!parentDir.exists()) {
            parentDir.mkdirs()
        }
    }
    if (!archivo.exists()) {
        archivo.createNewFile()
    }
}
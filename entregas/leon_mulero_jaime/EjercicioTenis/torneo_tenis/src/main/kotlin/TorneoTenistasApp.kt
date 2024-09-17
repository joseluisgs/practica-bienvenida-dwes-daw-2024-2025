package org.example

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextColors.Companion.rgb
import com.github.ajalt.mordant.terminal.Terminal
import org.example.tenistas.models.Tenista
import org.example.tenistas.services.TenistaService
import org.example.tenistas.storage.genericStorage.TenistaStorages
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.lighthousegames.logging.logging
import java.io.File
import kotlin.io.path.Path
import kotlin.system.exitProcess

private val logger = logging()
val terminal = Terminal()

class TorneoTenistasApp: KoinComponent {
    fun run(args: Array<String>) {

        val tenistasService: TenistaService by inject()
        val storages: TenistaStorages by inject()

        terminal.println(green("🎾 EJERCICIO TORNEO DE TENISTAS 🎾"))

        val archivoDeEntrada = File(args[0])

        if (args.size !in 1..2) {
            logger.error { "El comando de entrada es incorrecto" }
            terminal.println(red("Nº de argumentos incorrecto, ejemplo: java -jar torneo_tenis.jar fichero_entrada.csv fichero_salida.json\n"))
            exitProcess(0)
        }

        if (!archivoDeEntrada.exists()) {
            logger.error { "Archivo de entrada no existe" }
            terminal.println(red("El archivo de entrada no existe o es inaccesible\n"))
            exitProcess(0)
        }
        if (!archivoDeEntrada.isFile) {
            logger.error { "El archivo de entrada no es un tipo de fichero válido" }
            terminal.println(red("El archivo de entrada no es un tipo de fichero válido. Archivos permitidos: CSV\n"))
            exitProcess(0)
        }
        if (archivoDeEntrada.extension != "csv") {
            logger.error { "Archivo de entrada con extensión distinta a csv" }
            terminal.println(red("El archivo de entrada debe ser un archivo CSV\n"))
            exitProcess(0)
        }

        val listaTenistas = storages.loadCsv(archivoDeEntrada)

        if (listaTenistas.isOk) {
            listaTenistas.value.forEach { tenistasService.saveTenista(it) }
        } else {
            logger.error { "Error al cargar el fichero csv de tenistas: ${listaTenistas.error.message}" }
            terminal.println(red("Error al cargar el fichero csv de tenistas: ${listaTenistas.error.message}\n"))
            exitProcess(0)
        }

        val listadoTenistasBBDD = tenistasService.getAll().value

        terminal.println(rgb("#C200FF")("⚠️ CONSULTAS DE LOS TENISTAS ⚠️\n"))

        terminal.println(green("🔸 TENISTAS ORDENADOS CON RANKING, ES DECIR, POR PUNTOS DE MAYOR A MENOR\n"))
        listadoTenistasBBDD.sortedByDescending { it.puntos }.forEach { println(it) }

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 MEDIA DE ALTURA DE LOS TENISTAS\n"))
        println("${listadoTenistasBBDD.map { it.altura }.average()} cm")

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 MEDIA DE PESO DE LOS TENISTAS\n"))
        println("${listadoTenistasBBDD.map { it.peso }.average()} kg")

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 TENISTA MÁS ALTO\n"))
        val tenistaMasAlto = listadoTenistasBBDD.maxBy { it.altura }
        println("Tenista: ${tenistaMasAlto.nombre}")
        println("Altura: ${tenistaMasAlto.altura} cm")

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 TENISTAS DE ESPAÑA\n"))
        listadoTenistasBBDD.filter { it.pais == "España" }.forEach { println("${it.nombre}") }

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 TENISTAS AGRUPADOS POR PAÍS\n"))
        listadoTenistasBBDD.groupBy { it.pais }.forEach { pais, tenistas ->
            println("País: $pais")
            tenistas.forEach { println(" - ${it.nombre}\n") }
        }

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 NÚMERO DE TENISTAS AGRUPADOS POR PAÍS Y ORDENADOS POR PUNTOS DESCENDENTE\n"))
        listadoTenistasBBDD.groupBy { it.pais }
            .mapValues { tenistas ->
                tenistas.value.sortedByDescending { it.puntos }
            }
            .forEach { pais, tenistas ->
                println("País: $pais")
                tenistas.forEach { println("  - ${it.nombre} ~ ${it.puntos} puntos\n") }
            }

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 NÚMERO DE TENISTAS AGRUPADOS POR MANO DOMINANTE Y PUNTUACIÓN MEDIA DE ELLOS\n"))
        listadoTenistasBBDD.groupBy { it.mano }.forEach { mano, tenistas ->
            val puntuacionMedia = String.format("%.2f", tenistas.map { it.puntos }.average())
            println("Mano: $mano")
            println(" - Número de tenistas: ${tenistas.size}")
            println(" - Puntuación media: $puntuacionMedia puntos\n")
        }

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 PUNTUACIÓN TOTAL DE LOS TENISTAS AGRUPADOS POR PAÍS\n"))
        listadoTenistasBBDD.groupBy { it.pais }.forEach { pais, tenistas ->
            val puntuacionTotal = tenistas.sumOf { it.puntos }
            println("País: $pais")
            println(" - Puntuación total: $puntuacionTotal\n")
        }

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 PAÍS CON MAYOR PUNTUACION TOTAL\n"))
        val pais = listadoTenistasBBDD.groupBy { it.pais }.maxByOrNull { (_, tenistas) -> tenistas.sumOf { it.puntos } }
        println("Pais: ${pais?.key}")
        println(" - Puntuación total: ${pais?.value?.sumOf { it.puntos }}\n")

        terminal.println(blue("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"))

        terminal.println(green("🔸 TENISTA CON MEJOR RANKING DE ESPAÑA\n"))
        val tenista = listadoTenistasBBDD.filter { it.pais == "España" }.maxByOrNull { it.puntos }
        println("${tenista?.nombre}\n - Puntos: ${tenista?.puntos}\n")

        terminal.println(rgb("#C200FF")("👋🏼 FIN DE LAS CONSULTAS 👋🏼\n"))

        if (args.size == 2) {
            val archivoDeSalida = File(args[1])

            if (archivoDeSalida.extension != "csv" && archivoDeSalida.extension != "json" && archivoDeSalida.extension != "xml") {
                logger.error { "Archivo de salida con extensión distinta a csv, json o xml" }
                terminal.println(yellow("El archivo de salida debe ser un archivo CSV, JSON o XML\n"))
                terminal.println(yellow("Creando archivo JSON por defecto en la ruta: ${archivoDeEntrada.parent}"))
                val ficheroDeSalidaPorDefecto = Path("data", "tenistas.json").toFile()
                generarArchivoDeSalida(ficheroDeSalidaPorDefecto, listadoTenistasBBDD)
            }

            if (archivoDeSalida.extension == "csv" || archivoDeSalida.extension == "json" || archivoDeSalida.extension == "xml") {
                logger.debug { "Generando archivo de salida" }
                generarArchivoDeSalida(archivoDeSalida, listadoTenistasBBDD)
            }
        }

        if (args.size == 1) {
            val ficheroDeSalidaPorDefecto = Path("data", "tenistas.json").toFile()
            logger.debug { "Generando archivo de salida JSON por defecto en la ruta: ${archivoDeEntrada.parent}" }
            generarArchivoDeSalida(ficheroDeSalidaPorDefecto, listadoTenistasBBDD)
        }
    }

    private fun generarArchivoDeSalida(file: File, list: List<Tenista>) {
        val storages: TenistaStorages by inject()

        when (file.extension) {
            "csv" -> storages.storeCsv(file, list)
            "json" -> storages.storeJson(file, list)
            "xml" -> storages.storeXML(file, list)
        }
    }
}
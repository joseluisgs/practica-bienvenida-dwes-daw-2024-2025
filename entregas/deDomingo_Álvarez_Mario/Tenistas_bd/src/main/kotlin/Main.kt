package org.example

import com.github.michaelbull.result.fold
import org.example.service.storage.StorageCsvImpl
import org.example.tenistas.models.Tenista
import org.lighthousegames.logging.logging
import java.io.File
import kotlin.system.exitProcess

private val logger = logging()

fun main(args: Array<String>) {
    if (args.isNotEmpty() && args[0] == "data/data.csv") {
        val filePath = args[0]
        val file = File(filePath)

        logger.debug { "Cargando el archivo $filePath" }
        logger.debug { file }

        if (!file.exists()) {
            println("El archivo $filePath no existe.")
            exitProcess(1)
        }

        val storage = StorageCsvImpl()
        val result = storage.importFromCSV(file)

        result.fold(
            { tenistas ->
                consultas(tenistas)  // Llamamos a la función Consultas
            },
            { error ->
                println("Error al cargar los tenistas: ${error.message}")
                exitProcess(1)
            }
        )

    } else {
        println("Se necesita un archivo CSV válido como argumento.")
        exitProcess(1)
    }
}
/**
 * consultas sobre la lista de tenistas.
 */
fun consultas(tenistas: List<Tenista>) {
    // 1. Tenistas ordenados por ranking
    val tenistasOrdenadosPorRanking = tenistas.sortedByDescending { it.puntos }
    println("Tenistas ordenados por ranking (de mayor a menor puntos):")
    tenistasOrdenadosPorRanking.forEach { println("${it.nombre} - ${it.puntos} puntos") }

    // 2. Media de altura de los tenistas
    val mediaAltura = tenistas.map { it.altura }.average()
    println("Media de altura de los tenistas: $mediaAltura cm")

    // 3. Media de peso de los tenistas
    val mediaPeso = tenistas.map { it.peso }.average()
    println("Media de peso de los tenistas: $mediaPeso kg")

    // 4. Tenista más alto
    val tenistaMasAlto = tenistas.maxByOrNull { it.altura }
    println("Tenista más alto: ${tenistaMasAlto?.nombre} con ${tenistaMasAlto?.altura} cm")

    // 5. Tenistas de España
    val tenistasEspana = tenistas.filter { it.pais == "España" }
    println("Tenistas de España:")
    tenistasEspana.forEach { println(it.nombre) }

    // 6. Tenistas agrupados por país
    val tenistasAgrupadosPorPais = tenistas.groupBy { it.pais }
    println("Tenistas agrupados por país:")
    tenistasAgrupadosPorPais.forEach { (pais, tenistasPais) ->
        println("$pais: ${tenistasPais.joinToString { it.nombre }}")
    }

    // 7. Número de tenistas agrupados por país y ordenados por puntos descendentes
    println("Número de tenistas agrupados por país y ordenados por puntos descendentes:")
    tenistasAgrupadosPorPais.mapValues { it.value.sortedByDescending { tenista -> tenista.puntos } }
        .forEach { (pais, tenistasPais) ->
            println("$pais: ${tenistasPais.size} tenistas")
            tenistasPais.forEach { println("${it.nombre} - ${it.puntos} puntos") }
        }

    // 8. Número de tenistas agrupados por mano dominante y puntuación media de ellos
    val tenistasAgrupadosPorMano = tenistas.groupBy { it.mano }
    println("Número de tenistas agrupados por mano dominante y puntuación media de ellos:")
    tenistasAgrupadosPorMano.forEach { (mano, tenistasMano) ->
        val mediaPuntos = tenistasMano.map { it.puntos }.average()
        println("$mano: ${tenistasMano.size} tenistas, Media de puntos: $mediaPuntos")
    }

    // 9. Puntuación total de los tenistas agrupados por país
    val puntuacionTotalPorPais = tenistas.groupBy { it.pais }.mapValues { (_, tenistasPais) ->
        tenistasPais.sumOf { it.puntos }
    }
    println("Puntuación total de los tenistas agrupados por país:")
    puntuacionTotalPorPais.forEach { (pais, puntos) ->
        println("$pais: $puntos puntos")
    }

    // 10. País con más puntuación total
    val paisConMasPuntuacion = puntuacionTotalPorPais.maxByOrNull { it.value }
    println("País con más puntuación total: ${paisConMasPuntuacion?.key} con ${paisConMasPuntuacion?.value} puntos")

    // 11. Tenista con mejor ranking de España
    val mejorTenistaEspana = tenistasEspana.maxByOrNull { it.puntos }
    println("Tenista con mejor ranking de España: ${mejorTenistaEspana?.nombre} con ${mejorTenistaEspana?.puntos} puntos")
}

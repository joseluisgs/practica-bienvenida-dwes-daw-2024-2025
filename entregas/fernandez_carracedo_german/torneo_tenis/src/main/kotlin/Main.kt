package org.example

import com.github.ajalt.mordant.terminal.Terminal
import org.example.services.storage.tenista.TenistaFileStorageCsv
import org.example.services.tenistas.TenistasServiceImpl
import org.example.models.Tenista
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import org.example.cache.TenistaCache
import org.example.config.Config
import org.example.models.Mano
import org.example.repositories.tenista.TenistaRepositoryImpl
import org.example.services.database.SqlDeLightClient
import org.example.validators.TenistaValidator
import org.lighthousegames.logging.logging
import java.time.LocalDate


/**
 * Práctica de bienvenida DWES: Gestión de tenistas
 * @author Germán Fernández
 * @version 1.3
 */

private val logger = logging()
val term = Terminal()

fun main(args: Array<String>) {
    term.println (bold(brightBlue("Práctica de Bienvenida DWES")))

    val service=TenistasServiceImpl(
        TenistaRepositoryImpl(SqlDeLightClient),
        TenistaCache(Config.cacheSize),
        TenistaValidator(),
        TenistaFileStorageCsv())

    val listaTenistas = service.loadTenistasFromCsv("data.csv")

    term.println(bold(blue("Contenido importado del fichero CSV de tenistas:")))
    if (listaTenistas.isEmpty()) { println("No se ha importado ningún tenista del fichero") } else {
        listaTenistas.forEach { imprimirSegunPuntuacion(it) }
        term.println(bold(brightBlue("Total de tenistas importados: ${listaTenistas.size}")))
    }

    println()
    mostrarConsultas(listaTenistas)


    term.println(bold(blue("Volcado de tenistas a la BD y consultas desde la BD:")))
    if (listaTenistas.isEmpty()){ println("No hay tenistas para volcar a la BD") }
    else {
        listaTenistas.forEach { service.save(it) }
        term.println(bold(brightBlue("Total de tenistas volcados a la BD: ${service.getAll().value.size}")))
    }
    mostrarConsultasBD(service)

}

fun mostrarConsultasBD(service: TenistasServiceImpl) {

    service.getById(1).onSuccess{
        logger.debug { "Consulta por ID: ${it.toString()}" }
        term.println(bold(brightBlue("Tenista por ID (1):")))
        imprimirSegunPuntuacion(it)
    }.onFailure {
        logger.debug { "Error al consultar por ID: ${it.message}" }
        term.println(bold(brightRed("Error al consultar tenista por ID (1)")))
    }

    service.getAll().onSuccess {
        logger.debug { "Consulta todos los tenistas: " }
        term.println(bold(brightBlue("Todos los tenistas: ${it.size} ")))
        it.forEach { imprimirSegunPuntuacion(it) }
    }.onFailure {
        logger.debug { "Error al consultar todos los tenistas: ${it.message}" }
        term.println(bold(brightRed("Error al consultar todos los tenistas")))
    }

    service.getTenistaByRanking(3).onSuccess {
        logger.debug { "Consulta tenista por ranking: 3" }
        term.println(bold(brightBlue("Tenista por ranking (3):")))
        imprimirSegunPuntuacion(it)
    }.onFailure {
        logger.debug { "Error al consultar tenista por ranking: 3" }
        term.println(bold(brightRed("Error al consultar tenista por ranking (1)")))
    }

    service.save(Tenista(
        nombre = "Prueba",
        pais = "España",
        altura = 182,
        peso = 85,
        puntos = 7000,
        mano = Mano.DIESTRO,
        fechaNacimiento = LocalDate.of(1990, 5, 15)
    )).onSuccess {
        logger.debug { "Tenista guardado correctamente" }
        term.println(bold(brightGreen("Tenista guardado correctamente")))
        imprimirSegunPuntuacion(it)
    }.onFailure {
        logger.debug { "Error al guardar el tenista: ${it.message}" }
        term.println(bold(brightRed("Error al guardar el tenista")))
    }

    service.getTenistasByPais("España").onSuccess {
        logger.debug { "Consulta tenistas por país: España" }
        term.println(bold(brightBlue("Tenistas por país (España): ${it.size}")))
        it.forEach { imprimirSegunPuntuacion(it) }
    }.onFailure {
        logger.debug { "Error al consultar tenistas por país: España" }
        term.println(bold(brightRed("Error al consultar tenistas por país (España)")))
    }

    //TODO delete, update,

}

/**
 * Imprime un tenista según su ranking, utilizando colores para disitintos tramos dentro del ranking
 * @param tenista Tenista sobre el que imprimir los resultados
 * @see mordant.mordant.terminal.Text
 * @author Germán Fernández
 * @since 1.3
 */
private fun imprimirSegunPuntuacion(tenista: Tenista) {
    when (tenista.puntos) {
        0 -> { term.println(yellow(tenista.toString())) }
        in 0..1000 -> term.println(green(tenista.toString()))
        in 1001..3000 -> term.println(blue(tenista.toString()))
        in 3001..8000 -> term.println(brightMagenta(tenista.toString()))
        in 8001..Int.MAX_VALUE -> term.println(bold(brightRed(tenista.toString())))
    }
    //term.print(Text((yellow)("Texto"), align = LEFT, width = 10))
}

/**
 * Muestra las consultas solicitadas sobre la lista de tenistas, utilizando mordant para mostrar los resultados con colores
 * @param listaTenistas Lista de tenistas sobre la que realizar las consultas
 * @see mordant.mordant.terminal.Text
 * @author Germán Fernández
 * @since 1.3
 */
private fun mostrarConsultas(listaTenistas: List<Tenista>) {
    term.println(bold(blue("Consultas sobre la lista de tenistas:")))
    logger.debug {"Mostrando consultas"}
    // tenistas ordenados con ranking, es decir, por puntos de mayor a menor

    term.println(bold(blue("tenistas ordenados con ranking, por puntos de mayor a menor:")))
    listaTenistas.sortedByDescending { it.puntos }.forEach { imprimirSegunPuntuacion(it) }

    // media de altura de los tenistas
    term.print(bold(blue("Media de altura de los tenistas: ")))
    term.println(bold(brightBlue(listaTenistas.map{it.altura}.average().toString())))

    // media de peso de los tenistas
    term.print(bold(blue("Media de peso de los tenistas: ")))
    term.println(bold(brightBlue(listaTenistas.map{it.peso}.average().toString())))

    // tenista más alto
    // tenistas de España
    // tenistas agrupados por país
    // número de tenistas agrupados por país y ordenados por puntos descendente
    // numero de tenistas agrupados por mano dominante y puntuación media de ellos
    // puntuación total de los tenistas agrupados por país
    // país con más puntuación total
    // tenista con mejor ranking de España
}




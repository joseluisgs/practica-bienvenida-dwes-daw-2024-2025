package org.example.tenista.util

import org.example.tenista.models.Tenista


class Consultas {

    fun mostrarConsultas(tenistas: List<Tenista>) {
        val tenistasRanking = tenistas.sortedByDescending { it.ranking }
        println("Tenistas ordenados por ranking: $tenistasRanking")

        val mediaAltura = tenistas.map { it.altura }.average()
        println("Media de altura: $mediaAltura")

        val mediaPeso = tenistas.map { it.peso }.average()
        println("Media de peso: $mediaPeso")

        val tenistaMasAlto = tenistas.maxByOrNull { it.altura }
        println("Tenista más alto: $tenistaMasAlto")

        val tenistasEspana = tenistas.filter { it.pais == "España" }
        println("Tenistas de España: $tenistasEspana")

        val tenistasPorPais = tenistas.groupBy { it.pais }
        println("Tenistas agrupados por país: $tenistasPorPais")

        val tenistasPorPaisOrdenados = tenistasPorPais.mapValues { entry ->
            entry.value.sortedByDescending { it.ranking }
        }
        println("Tenistas por país ordenados por ranking: $tenistasPorPaisOrdenados")

        val tenistasPorMano = tenistas.groupBy { it.mano }
        val puntuacionMediaPorMano = tenistasPorMano.mapValues { entry ->
            entry.value.map { it.ranking }.average()
        }
        println("Ranking medio por mano dominante: $puntuacionMediaPorMano")

        val rankingTotalPorPais = tenistasPorPais.mapValues { entry ->
            entry.value.sumOf { it.ranking }
        }
        println("Ranking total por país: $rankingTotalPorPais")

        val paisMasRanking = rankingTotalPorPais.maxByOrNull { it.value }
        println("País con más ranking total: $paisMasRanking")

        val mejorTenistaEspana = tenistasEspana.maxByOrNull { it.ranking }
        println("Tenista con mejor ranking de España: $mejorTenistaEspana")
    }
}
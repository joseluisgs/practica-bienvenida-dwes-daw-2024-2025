package org.example

import org.example.tenista.models.Mano
import org.example.tenista.models.Tenista
import org.example.tenista.repository.TenistasRepositoryImpl
import java.time.LocalDate

fun main() {
    // Crear una lista de tenistas
    val tenistas = listOf(
        Tenista(
            nombre = "Tenista 1",
            pais = "País 1",
            altura = 180,
            peso = 75.0,
            puntos = 2000,
            mano = Mano.DIESTRO,
            fechaNacimiento = LocalDate.of(1990, 1, 1)
        ),
        Tenista(
            nombre = "Tenista 2",
            pais = "País 2",
            altura = 185,
            peso = 78.0,
            puntos = 1500,
            mano = Mano.ZURDO,
            fechaNacimiento = LocalDate.of(1992, 2, 2)
        )
        // Agrega más tenistas aquí
    )

    // Crear una instancia del repositorio
    val repository = TenistasRepositoryImpl()

    // Guardar cada tenista en la base de datos
    tenistas.forEach { tenista ->
        repository.save(tenista)
    }

    println("Todos los tenistas han sido guardados en la base de datos.")

    // Recuperar y mostrar todos los tenistas
    val allTenistas = repository.findAll()
    println("Todos los tenistas en la base de datos:")
    allTenistas.forEach { tenista ->
        println(tenista)
    }
}

package org.example

import models.Mano
import models.Tenista
import org.example.repositories.tenista.TenistaRepository
import org.example.repositories.tenista.TenistaRepositoryImpl

fun main(args: Array<String>) {
    val repository = TenistaRepositoryImpl()

    println("Iniciando base de datos de Tenistas")

   /* // Pendiente java jar. Prueba no ejecutar */
    if (1 == 2 && args.size < 1) {
        println("Uso: java -jar torneo_tenis.jar <fichero_entrada.csv> [fichero_salida.xxx]")
        return
    }

    /*
    // leer csv
    var datosCsv;
    for(datosCsv in args)
    {
        val tenista = Tenista(1,
            "nombre",
            "pais", 180, 100, 2, Mano.DIESTRO.toString(), "fecha", null, null
        );
        val ten = repository.save(tenista)
    }
    */

    val tenista = Tenista(1,
         "Christian",
        "España", 176, 68, 15, Mano.DIESTRO.toString(), "fecha", null, null
    );

    val ten = repository.save(tenista)
    println("El tenista creado es : " + ten)



    /*
    val ficheroEntrada = args[0]
    val ficheroSalida = if (args.size > 1) args[1] else "torneo_tenis.json"

    val tenistas = leerFicheroEntrada(ficheroEntrada)
    val repository: TenistaRepository = TenistaRepositoryImpl(Database.connection)

    tenistas.forEach { repository.insertar(it) }

    generarFicheroSalida(tenistas, ficheroSalida)
    */
}
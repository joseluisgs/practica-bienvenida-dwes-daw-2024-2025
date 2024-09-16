package org.example.main

import org.example.tenista.repository.TenistasRepositoryImpl
import org.example.service.ReadFile
import org.example.service.Write
import org.example.tenista.util.Consultas
import java.io.File


fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Uso: java -jar torneo_tenis.jar <fichero_entrada.csv> <fichero_salida.json>")
        return
    }

    // Ruta del archivo CSV de entrada
    val inputFilePath = args[0]

    // Obtener el nombre del archivo sin extensión
    val inputFile = File(inputFilePath)

    // Ruta del archivo de salida con la misma ruta pero con extensión .json
    val outputFilePath = inputFile.parent + args[1]

    // Crear una instancia del servicio de lectura de archivos
    val readFileService = ReadFile()

    // Leer y procesar los tenistas desde el archivo CSV
    val tenistas = readFileService.leerCSV(inputFilePath)

    if (tenistas != null) {
        // Crear una instancia del repositorio de tenistas
        val tenistasRepository = TenistasRepositoryImpl()

        // Guardar o actualizar tenistas en la base de datos
        tenistas.forEach { tenista ->
            tenistasRepository.saveOrUpdate(tenista)
        }

        // Obtener todos los tenistas de la base de datos
        val allTenistas = tenistasRepository.findAll()

        // Crear una instancia del servicio de escritura de archivos
        val writeService = Write()

        // Escribir todos los tenistas en el archivo de salida
        writeService.escribirTenistas(outputFilePath, allTenistas)

        val consultas = Consultas()
        consultas.mostrarConsultas(allTenistas)

        println("Todos los tenistas han sido guardados o actualizados en la base de datos y escritos en el archivo de salida: $outputFilePath")
    } else {
        println("No se pudieron leer los tenistas del archivo.")
    }
}

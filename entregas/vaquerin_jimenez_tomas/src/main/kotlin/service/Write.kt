package org.example.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.example.tenista.models.Tenista
import java.io.FileWriter

class Write {

    fun escribirTenistas(rutaSalida: String, tenistas: List<Tenista>) {
        when {
            rutaSalida.endsWith(".csv") -> escribirCSV(rutaSalida, tenistas)
            rutaSalida.endsWith(".json") -> escribirJSON(rutaSalida, tenistas)
            rutaSalida.endsWith(".xml") -> escribirXML(rutaSalida, tenistas)
            else -> escribirJSON("torneo_tenis.json", tenistas) // Por defecto JSON
        }
    }

    private fun escribirCSV(rutaSalida: String, tenistas: List<Tenista>) {
        java.io.PrintWriter(rutaSalida).use { out ->
            tenistas.forEach { tenista ->
                out.println(
                    "${tenista.id}," +
                            "${tenista.nombre}," +
                            "${tenista.pais}," +
                            "${tenista.altura}," +
                            "${tenista.peso}," +
                            "${tenista.puntos}," +
                            "${tenista.mano}," +
                            "${tenista.fechaNacimiento}," +
                            "${tenista.createdAt}," +
                            "${tenista.updatedAt}," +
                            "${tenista.ranking}"
                )
            }
        }
        println("Archivo CSV creado en: $rutaSalida")
    }

    private fun escribirJSON(rutaSalida: String, tenistas: List<Tenista>) {
        val mapper = ObjectMapper().registerModule(JavaTimeModule()).writerWithDefaultPrettyPrinter()
        mapper.writeValue(FileWriter(rutaSalida), tenistas)
        println("Archivo JSON creado en: $rutaSalida")
    }

    private fun escribirXML(rutaSalida: String, tenistas: List<Tenista>) {
        val xmlMapper = XmlMapper().registerModule(JavaTimeModule()).enable(SerializationFeature.INDENT_OUTPUT)
        xmlMapper.writeValue(FileWriter(rutaSalida), tenistas)
        println("Archivo XML creado en: $rutaSalida")
    }
}
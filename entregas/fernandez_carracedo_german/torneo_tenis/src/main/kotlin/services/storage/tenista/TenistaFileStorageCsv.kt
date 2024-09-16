package org.example.services.storage.tenista

import org.example.config.Config
import org.example.dto.TenistaDto
import org.example.exceptions.StorageExceptions
import org.example.models.Tenista
import org.example.mappers.toTenista
import org.example.models.Mano
import org.example.services.storage.base.FileStorageRead
import org.example.services.storage.base.FileStorageWrite
import org.lighthousegames.logging.logging
import java.io.File
import java.nio.file.Paths

private val logger = logging()

/**
 *
 */
class TenistaFileStorageCsv: FileStorageRead<Tenista>, FileStorageWrite<Tenista> {
    /**
     * Lee los tenistas del fichero CSV y los devuelve como una lista de Tenista.
     * @param file Fichero CSV donde se encuentran los tenistas
     * @return Lista de Tenista leídos del fichero
     */
    override fun readFromFile(fileName: String): List<Tenista> {

        logger.debug { "Leyendo de fichero CSV: $fileName" }

        val file = Paths.get(Config.storageData, fileName).toFile()

        if (!file.exists()) {
            logger.error { "El fichero no existe: $file" }
            throw StorageExceptions.FileNotFound("El fichero no existe: $file")
        }

        if (!file.canRead()) {
            logger.error { "El fichero no se puede leer: $file" }
            throw StorageExceptions.FileNotReadable("El fichero no se puede leer: $file")
        }

        //TODO Comprobar:
        //  - número de columnas correcto
        //  - formato correcto primera línea del fichero
        //  - en cada línea, el formato de cada dato
        return file.readLines()
            //.drop(1)
            .drop(if(tieneCabecera(file)) 1 else 0)
            .map {
                it.split(",")
            }.map {
                if(comprobarLinea(it)) {
                TenistaDto(
                    id = it[0].toLong(),
                    nombre = it[1],
                    pais = it[2],
                    altura = it[3].toInt(),
                    peso = it[4].toInt(),
                    puntos = it[5].toInt(),
                    mano = it[6],
                    fechaNacimiento = it[7],
                ).toTenista()
                } else {
                    TenistaDto(id=-1, nombre = "", pais = "", altura = 0, peso = 0, puntos = 0, mano = Mano.DIESTRO.toString()).toTenista()}
                }.filterNot { it.id.toInt() == -1 }
    }


    /**
     * Comprueba si la línea tiene el formato correcto para una tenista
     * @param it Lista de strings con los valores de la línea
     */
    private fun comprobarLinea(it: List<String>): Boolean {
        return true
    }

    /**
     * Comprueba si el fichero tiene cabecera (primera línea) con los nombres de los campos
     * @param file Fichero del que comprobar si tiene cabecera
     */
    private fun tieneCabecera(file: File): Boolean {
        return file.readLines().first().contains("id,nombre,pais,altura,peso,puntos,mano,fecha_nacimiento")
    }

    /**
     * Escribe la lista de tenistas en un fichero CSV
     * @param list Lista de tenistas a escribir en el fichero
     * @param file Fichero en el que escribir la lista de tenistas
     */
    override fun writeToFile(list: List<Tenista>, file: File) {
        val cabecera = "id,nombre,pais,altura,peso,puntos,mano,fecha_nacimiento\n"
        file.writeText(cabecera)
        list.forEach{
            file.appendText("${it.id},${it.nombre},${it.pais},${it.altura},${it.peso},${it.puntos},${it.mano},${it.fechaNacimiento}\n")
        }

    }
}
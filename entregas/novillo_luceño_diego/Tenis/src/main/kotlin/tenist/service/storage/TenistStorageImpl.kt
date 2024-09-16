package org.example.tenist.service.storage

import com.github.michaelbull.result.Result
import org.example.tenist.errors.TenistError
import org.example.tenist.models.Tenist
import org.example.tenist.service.storage.csv.TenistStorageCsv
import org.example.tenist.service.storage.json.TenistStorageJson
import org.example.tenist.service.storage.xml.TenistStorageXml
import org.koin.core.annotation.Singleton
import java.io.File
import java.nio.file.Path

@Singleton
class TenistStorageImpl(
    private val csv: TenistStorageCsv,
    private val json: TenistStorageJson,
    private val xml: TenistStorageXml,
) : TenistStorage{

    /**
     * Una funcion que sirve para exportar un fichero Json que contenga todos los tenistas que se encuentran
     * en la lista dada
     * @param list que es la lista de tenistas que se quiere exportar
     * @param file el fichero que puede o no ser nulo, en el caso de que sea nulo se creara uno
     * @return un result que contiene un Unit en el caso de que se haya realizado correctamente o
     * un TenistaError si no.
     */
    override fun exportToJson(file : File?, list: List<Tenist>): Result<Unit, TenistError> {
        if(file == null) {
            val newFile = File("data${File.separator}tenistas","tenistas.json")
            File("data").mkdirs()
            newFile.writeText("")
            return json.export(newFile, list)
        }
        return json.export(file, list)
    }

    /**
     * Una funcion que sirve para exportar un fichero CSV que contenga todos los tenistas que se encuentran
     * en la lista dada
     * @param list que es la lista de tenistas que se quiere exportar
     * @param file el fichero que puede o no ser nulo, en el caso de que sea nulo se creara uno
     */
    override fun exportToCsv(file : File?, list: List<Tenist>): Result<Unit, TenistError> {
        if(file == null) {
            val newFile = File("data","tenistas.csv")
            File("data").mkdirs()
            newFile.writeText("")
            return csv.export(newFile, list)
        }
        return csv.export(file, list)
    }

    /**
     * Una funcion que sirve para exportar un fichero XML que contenga todos los tenistas que se encuentran
     * en la lista dada
     * @param list que es la lista de tenistas que se quiere exportar
     * @param file el fichero que puede o no ser nulo, en el caso de que sea nulo se creara uno
     */
    override fun exportToXml(file : File?, list: List<Tenist>): Result<Unit, TenistError> {
        if(file == null) {
            val newFile = File("data","tenistas.xml")
            File("data").mkdirs()
            newFile.writeText("")
            return xml.export(newFile, list)
        }
        return xml.export(file, list)
    }
}
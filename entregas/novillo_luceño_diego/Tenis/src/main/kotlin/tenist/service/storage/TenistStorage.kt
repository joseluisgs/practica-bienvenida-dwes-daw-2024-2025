package org.example.tenist.service.storage

import com.github.michaelbull.result.Result
import org.example.tenist.errors.TenistError
import org.example.tenist.models.Tenist
import java.io.File

interface TenistStorage {
    fun importFromJson(file : File) : Result<List<Tenist>,TenistError>
    fun exportToJson(file : File?, list : List<Tenist>) : Result<Unit, TenistError>
    fun exportToCsv(file : File?, list : List<Tenist>) : Result<Unit,TenistError>
    fun exportToXml(file : File?, list : List<Tenist>) : Result<Unit,TenistError>
}
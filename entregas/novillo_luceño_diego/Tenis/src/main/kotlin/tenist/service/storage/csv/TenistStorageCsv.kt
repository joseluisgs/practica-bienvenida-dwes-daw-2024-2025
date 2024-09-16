package org.example.tenist.service.storage.csv

import com.github.michaelbull.result.Result
import org.example.tenist.errors.TenistError
import org.example.tenist.models.Tenist
import java.io.File

interface TenistStorageCsv {
    fun export(file : File, list: List<Tenist>) : Result<Unit, TenistError>
    fun import(file : File) : Result<List<Tenist>, TenistError>
}
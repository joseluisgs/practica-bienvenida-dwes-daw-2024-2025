package org.example.tenist.service.storage.xml

import com.github.michaelbull.result.Result
import org.example.tenist.errors.TenistError
import org.example.tenist.models.Tenist
import java.io.File

interface TenistStorageXml {
    fun export(file : File, list : List<Tenist>) : Result<Unit, TenistError>
}
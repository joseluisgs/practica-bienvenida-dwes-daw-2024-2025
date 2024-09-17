package org.example.tenist.service.storage.xml

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import org.example.tenist.errors.TenistError
import org.example.tenist.mappers.toTenistDto
import org.example.tenist.models.Tenist
import org.koin.core.annotation.Singleton
import java.io.File

@Singleton
class TenistStorageXmlImpl : TenistStorageXml{
    override fun export(file: File, list: List<Tenist>): Result<Unit, TenistError> {
        try {
            val xmlString = XML { indent = 4; indentString = "   "}
            val data = list.map { it.toTenistDto() }
            file.writeText(xmlString.encodeToString(data))
            return Ok(Unit)
        }catch (e : Exception) {
            return Err(TenistError.ExportError("No se ha podido exportar el fichero XML: $e"))
        }
    }
}
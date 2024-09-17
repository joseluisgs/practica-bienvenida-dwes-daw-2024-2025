package org.example.tenistas.storage.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.tenistas.errors.TenistaError
import org.example.tenistas.models.Tenista
import java.time.LocalDate

class CsvValidator {
    fun validate(item: List<String>): Result<List<String>, TenistaError> {

        val regexNombres = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ'-]+(\\s+[a-zA-ZáéíóúÁÉÍÓÚñÑ'-]+)*\$")
        val regexMano = Regex("^(DIESTRO|ZURDO)\$")
        val regexFechaISO = Regex("^\\d{4}-\\d{2}-\\d{2}\$")

        return when {
            item[0].isEmpty() -> Err(TenistaError.TenistaNoValido("El ID no puede ser un campo vacío"))
            item[0].isBlank() -> Err(TenistaError.TenistaNoValido("El ID no puede ser un campo vacío"))
            item[0].toIntOrNull() == null -> Err(TenistaError.TenistaNoValido("El ID debe ser un número entero: ${item[0]}"))
            item[0].toInt() < 0 -> Err(TenistaError.TenistaNoValido("El ID no puede ser un campo negativo: ${item[0]}"))

            item[1].isEmpty() -> Err(TenistaError.TenistaNoValido("El nombre no puede ser un campo vacío"))
            item[1].isBlank() -> Err(TenistaError.TenistaNoValido("El nombre no puede ser un campo vacío"))
            !item[1].matches(regexNombres) -> Err(TenistaError.TenistaNoValido("El nombre solo puede contener letras: ${item[1]}"))

            item[2].isEmpty() -> Err(TenistaError.TenistaNoValido("El pais no puede ser un campo vacío"))
            item[2].isBlank() -> Err(TenistaError.TenistaNoValido("El pais no puede ser un campo vacío"))
            !item[2].matches(regexNombres) -> Err(TenistaError.TenistaNoValido("El pais solo puede contener letras: ${item[2]}"))

            item[3].toIntOrNull() == null -> Err(TenistaError.TenistaNoValido("La altura debe ser un número entero (en cm): ${item[3]}"))
            item[3].toInt() <= 0 -> Err(TenistaError.TenistaNoValido("La altura no puede ser un campo negativo: ${item[3]}"))

            item[4].toIntOrNull() == null -> Err(TenistaError.TenistaNoValido("El peso debe ser un número entero (en kg): ${item[4]}"))
            item[4].toInt() <= 0 -> Err(TenistaError.TenistaNoValido("El peso no puede ser un campo negativo: ${item[4]}"))

            item[5].toIntOrNull() == null -> Err(TenistaError.TenistaNoValido("Los puntos deben ser un número entero: ${item[5]}"))
            item[5].toInt() < 0 -> Err(TenistaError.TenistaNoValido("Los puntos no pueden ser un campo negativo: ${item[5]}"))

            item[6].isEmpty() -> Err(TenistaError.TenistaNoValido("La mano no puede ser un campo vacío"))
            item[6].isBlank() -> Err(TenistaError.TenistaNoValido("La mano no puede ser un campo vacío"))
            !item[6].uppercase().matches(regexMano) -> Err(TenistaError.TenistaNoValido("La mano solo puede ser 'DIESTRO' o 'ZURDO': ${item[6]}"))

            item[7].isEmpty() -> Err(TenistaError.TenistaNoValido("La fecha de nacimiento no puede ser un campo vacío"))
            item[7].isBlank() -> Err(TenistaError.TenistaNoValido("La fecha de nacimiento no puede ser un campo vacío"))
            !item[7].matches(regexFechaISO) -> Err(TenistaError.TenistaNoValido("La fecha de nacimiento debe tener el formato 'AAAA-MM-DD': ${item[7]}"))
            item[7] > LocalDate.now().toString() -> Err(TenistaError.TenistaNoValido("La fecha de nacimiento no puede ser superior a la fecha actual: ${item[7]}"))

            else -> Ok(item)
        }
    }
}
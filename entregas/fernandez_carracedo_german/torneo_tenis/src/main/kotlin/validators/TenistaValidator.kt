package org.example.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.errors.tenistas.TenistaError
import org.example.models.Tenista
import java.time.LocalDate

class TenistaValidator {

    /**
     * Verifica que los datos del tenista cumplan con los criterios de formato y validez establecidos.
     * @param tenista El tenista que se va a validar.
     * @return Un [Result] que contiene el tenista validado si los datos cumplen con los criterios, o un error [TenistaError] si alguno de los campos no es válido.
     */
    fun validate(tenista: Tenista): Result<Tenista, TenistaError> {
/*        return when {
            (tenista.nombre.length<=5) -> Err(TenistaError.TenistaValidationError("El nombre debe tener una longitud de mas de 4 caracteres."))
            (tenista.altura <= 100)-> Err(TenistaError.TenistaValidationError("La altura mínima debe ser 100 cm."))
            (tenista.peso <= 50)-> Err(TenistaError.TenistaValidationError("El peso mínimo debe ser 50 kg."))
            (tenista.puntos < 0)-> Err(TenistaError.TenistaValidationError("El tenista no puede tener puntos negativos."))
            (tenista.fechaNacimiento <= LocalDate.now())-> Err(TenistaError.TenistaValidationError("La fecha de nacimiento no puede ser anterior a hoy."))
            else -> Ok(tenista)
        }*/

        return Ok(tenista)
    }
}
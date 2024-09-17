package org.example.tenist.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.tenist.errors.TenistError
import org.example.tenist.models.Tenist

class TenisValidatorImpl : TenistValidator {
    /**
     * Una funcion que válida si el objeto tenista es válido o no
     * @param tenist el tenista que se quiere analizar
     * @return
     */
    override fun validate(tenist: Tenist): Result<Tenist,TenistError> {
        if (tenist.name.isEmpty()) return Err(TenistError.InvalidTenist("El nombre  no puede estar vacío."))
        if (tenist.country.isEmpty()) return Err(TenistError.InvalidTenist("No puede tener el pais vacío"))
        if (tenist.weight < 0 || tenist.weight > 400) return Err(TenistError.InvalidTenist("El peso del tenista ${tenist.name} no es correcta."))
        if (tenist.height < 0 || tenist.height > 400) return Err(TenistError.InvalidTenist("La altura del tenista es incorrecta"))
        return Ok(tenist)
    }
}
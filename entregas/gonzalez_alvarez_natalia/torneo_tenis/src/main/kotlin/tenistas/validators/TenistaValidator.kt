package tenistas.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import tenistas.errors.TenistaError
import tenistas.models.Tenista
import java.time.LocalDate

/**
 * Validador para los tenistas.
 *
 * @author Natalia González
 * @version 1.0
 */
class TenistaValidator {

    /**
     * Valida un tenista según las reglas definidas.
     *
     * @param tenista El objeto [Tenista] a validar.
     * @return Un [Result] que contiene el tenista si todas las validaciones son exitosas,
     *         o un [TenistaError] con el mensaje de error correspondiente si alguna validación falla.
     */
    fun validate(tenista: Tenista): Result<Tenista, TenistaError> {
        val nombreRegex = Regex("^[a-zA-Z]+ [a-zA-Z]+(?: [a-zA-Z]+)?\$")  // Expresión regular para validar el nombre
        val paisRegex = Regex("^[a-zA-Z\\s]{2,50}\$")  // Expresión regular para validar el país
        val manoRegex = Regex("^(?:DIESTRO|ZURDO)\$")  // Expresión regular para validar la mano

        return when {
            !tenista.nombre.matches(nombreRegex) -> Err(TenistaError.ValidatorTenistaError("El nombre no tiene el formato correcto"))
            tenista.nombre.isBlank() -> Err(TenistaError.ValidatorTenistaError("El nombre no puede estar vacío"))
            tenista.pais.isBlank() -> Err(TenistaError.ValidatorTenistaError("El país no puede estar vacío"))
            !tenista.pais.matches(paisRegex) -> Err(TenistaError.ValidatorTenistaError("El país no tiene el formato correcto"))
            tenista.altura <= 0 -> Err(TenistaError.ValidatorTenistaError("La altura no puede ser menor que 0"))
            tenista.peso <= 0 -> Err(TenistaError.ValidatorTenistaError("El peso no puede ser menor que 0"))
            tenista.puntos < 0 -> Err(TenistaError.ValidatorTenistaError("Los puntos no pueden ser negativos"))
            !tenista.mano.name.matches(manoRegex) -> Err(TenistaError.ValidatorTenistaError("La mano no tiene el formato correcto"))
            tenista.fecha_nacimiento > LocalDate.now() -> Err(TenistaError.ValidatorTenistaError("La fecha de nacimiento no puede ser posterior a hoy"))
            else -> Ok(tenista)
        }
    }
}
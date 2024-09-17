package validator

import org.example.tenistas.models.Tenista
import org.example.models.Mano
import java.time.LocalDate

object TenistaValidador {

    fun validate(tenista: Tenista): ValidationResult {
        val errors = mutableListOf<String>()

        // Validar nombre
        if (tenista.nombre.isBlank()) errors.add("El nombre no puede estar vacío.")

        // Validar país
        if (tenista.pais.isBlank()) errors.add("El país no puede estar vacío.")

        // Validar altura
        if (tenista.altura <= 0) errors.add("La altura debe ser un valor positivo.")

        // Validar peso
        if (tenista.peso <= 0) errors.add("El peso debe ser un valor positivo.")

        // Validar puntos
        if (tenista.puntos < 0) errors.add("Los puntos no pueden ser negativos.")

        // Validar fecha de nacimiento
        if (tenista.fechaNacimiento.isAfter(LocalDate.now()))
            errors.add("La fecha de nacimiento no puede ser una fecha futura.")

        // Validar mano
        if (tenista.mano !in Mano.values())
            errors.add("El valor para mano no es válido.")

        return if (errors.isEmpty()) {
            ValidationResult.Valid
        } else {
            ValidationResult.Invalid(errors)
        }
    }

    sealed class ValidationResult {
        object Valid : ValidationResult()
        data class Invalid(val errors: List<String>) : ValidationResult()
    }
}

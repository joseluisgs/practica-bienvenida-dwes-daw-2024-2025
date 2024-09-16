package validator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import tenista.validator.TenistaValidator

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TenistaValidatorTest {

    private val validator = TenistaValidator()

    @Test
    @DisplayName("Validación de CSV con el número correcto de argumentos")
    fun casoNumeroArgumentosCorrecto() {
        val resultado1 = validator.validarCsv(arrayOf("archivo.csv"))
        assertTrue(resultado1)

        val resultado2 = validator.validarCsv(arrayOf("archivo.csv", "arg_opcional"))
        assertTrue(resultado2)
    }

    @Test
    @DisplayName("Validación de CSV con el número incorrecto de argumentos")
    fun casoNumeroArgumentosIncorrecto() {
        val resultado = validator.validarCsv(arrayOf())
        assertFalse(resultado)

        val resultado2 = validator.validarCsv(arrayOf("archivo.csv", "arg_extra", "otro_arg"))
        assertFalse(resultado2)
    }

    @Test
    @DisplayName("Validación de archivo con extensión incorrecta")
    fun casoExtensionIncorrecta() {
        val resultado1 = validator.validarCsv(arrayOf("archivo.txt"))
        assertFalse(resultado1)

        val resultado2 = validator.validarCsv(arrayOf("archivo.docx"))
        assertFalse(resultado2)

        val resultado3 = validator.validarCsv(arrayOf("archivo"))
        assertFalse(resultado3)
    }

    @Test
    @DisplayName("Validación de archivo con extensión .csv")
    fun casoExtensionCorrecta() {
        val resultado1 = validator.validarCsv(arrayOf("archivo.csv"))
        assertTrue(resultado1)

        val resultado2 = validator.validarCsv(arrayOf("archivo.csv", "arg_opcional"))
        assertTrue(resultado2)
    }
}

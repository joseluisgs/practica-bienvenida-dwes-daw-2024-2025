package tenista.storage

import com.github.michaelbull.result.Result
import org.example.persona.errors.TenistaError
import org.example.persona.models.Tenista
import java.io.File

interface Storage {

    fun load(file: File): Result<List<Tenista>, TenistaError>
    fun store(file: File, listPersonas: List<Tenista>) : Result<Unit, TenistaError>

}
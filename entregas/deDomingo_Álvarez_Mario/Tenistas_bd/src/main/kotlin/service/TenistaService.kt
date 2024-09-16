import org.example.tenistas.models.Tenista
import org.example.tenistas.repository.TenistaRepository

class TenistaService(private val repository: TenistaRepository) {
    fun getAllTenistas(): List<Tenista> = repository.findAll()

    fun getTenistaById(id: Int): Tenista? = repository.findById(id)

    fun createTenista(tenista: Tenista): Tenista = repository.save(tenista)

    fun updateTenista(tenista: Tenista): Tenista = repository.update(tenista)

    fun deleteTenista(id: Int): Boolean = repository.delete(id)
}
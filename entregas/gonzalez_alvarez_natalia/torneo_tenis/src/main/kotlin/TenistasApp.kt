import database.SqlDelightManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import storage.StorageCsv
import storage.StorageJson
import storage.StorageXml
import tenistas.repositories.TenistaRepository
import tenistas.services.TenistaService
import java.io.File

class TenistasApp : KoinComponent {

    val servicio: TenistaService by inject()
    val sqlDelightManager: SqlDelightManager by inject()
    val storageCsv: StorageCsv by inject()
    val storageJson: StorageJson by inject()
    val storageXml: StorageXml by inject()

    fun run() {
        sqlDelightManager.initQueries()

        val lista = servicio.getAll().value
        lista.forEach { println(it) }

        val listaCsv = storageCsv.load(File("data","data.csv"))
        listaCsv.value.forEach { println(it) }
        storageCsv.save(lista, File("data","torneo_tenis.csv"))

        storageJson.save(lista, File("data","torneo_tenis.json"))
        storageXml.save(lista, File("torneo_tenis.xml"))
    }
}
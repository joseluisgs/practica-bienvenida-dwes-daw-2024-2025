import cache.CacheTenistasImpl
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import config.Config
import database.SqlDelightManager
import org.lighthousegames.logging.logging
import tenista.repository.TenistaRepositoryImpl
import tenista.services.TenistaServiceImpl
import tenista.storages.StorageCsv
import tenista.validator.TenistaValidator
import kotlin.io.path.Path

private val logger = logging()

val terminal = Terminal()

fun main(args:Array<String>){

    val servicio = TenistaServiceImpl(
        TenistaRepositoryImpl(SqlDelightManager(Config)),
        CacheTenistasImpl(5),
        StorageCsv()
    )


    val filePersonasCsv = Path("src","main","resources","data.csv").toFile()
    servicio.leer(filePersonasCsv)
    val listaTenistas = servicio.getAll()


    println("_*_*_*_*_*_*_*_*_CONSULTAS CON COLECCIONES_*_*_*_*_*_*_*_*_*_*_")


    terminal.println(TextColors.blue("TENISTAS ORDENADOR POR RANKING (MAYOR PUNTUAJE)\n"))
}


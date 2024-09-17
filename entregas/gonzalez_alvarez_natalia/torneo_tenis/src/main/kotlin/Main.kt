import di.tenistaModule
import org.koin.core.context.startKoin
import org.koin.test.verify.verify
import storage.StorageCsv
import java.io.File

fun main(args: Array<String>) {

    startKoin {
        printLogger()
        tenistaModule.verify()
        modules(tenistaModule)
    }

    val app = TenistasApp()
    app.run()


    //Consultas:
    val storageCsv = StorageCsv()
    val tenistas = storageCsv.load(File("data", "data.csv")).value

    println("Tenistas ordenados con ranking, es decir, por puntos de mayor a menor: ")
    tenistas.sortedByDescending { it.puntos }.forEach { println(it) }

    val mediaAltura = tenistas.map { it.altura }.average()
    println("Media de altura de los tenistas: $mediaAltura")

    val mediaPeso = tenistas.map { it.peso }.average()
    println("Media de peso de los tenistas: $mediaPeso")

    val tenistaMasAlto = tenistas.maxByOrNull { it.altura }
    println("Tenista más alto: $tenistaMasAlto")

    println("Tenistas de España: ")
    tenistas.filter { it.pais == "España" }.forEach { println(it) }

    println("Tenistas agrupados por país: ")
    tenistas.groupBy { it.pais }.forEach { println(it) }

    println("Número de tenistas agrupados por país y ordenados por puntos descendente: ")
    tenistas.groupBy { it.pais }
        .mapValues { (_, tenistasGrupo) -> tenistasGrupo.sortedByDescending { it.puntos } }
        .forEach { (pais, tenistasOrdenados) ->
            println("País: $pais, Número de tenistas: ${tenistasOrdenados.size}")
            tenistasOrdenados.forEach { println(it) }
        }

    println("Número de tenistas agrupados por mano dominante y puntuación media de ellos: ")
    tenistas.groupBy { it.mano }
        .forEach { (mano, tenistasGrupo) ->
            val mediaPuntos = tenistasGrupo.map { it.puntos }.average()
            println("Mano dominante: $mano, Número de tenistas: ${tenistasGrupo.size}, Puntuación media: $mediaPuntos")
        }

    println("Puntuación total de los tenistas agrupados por país: ")
    tenistas.groupBy { it.pais }
        .mapValues { (_, tenistasGrupo) -> tenistasGrupo.sumOf { it.puntos } }
        .forEach { (pais, puntuacionTotal) ->
            println("$pais: $puntuacionTotal puntos")
        }

    val paisPuntuacionTotal = tenistas.groupBy { it.pais }
        .maxByOrNull { (_, tenistasGrupo) -> tenistasGrupo.sumOf { it.puntos } }
    println("País con más puntuación total: $paisPuntuacionTotal")

    val tenistaMejorRank = tenistas.filter { it.pais == "España" }
        .maxByOrNull { it.puntos }
    println("Tenista con mejor ranking de España: $tenistaMejorRank")
}
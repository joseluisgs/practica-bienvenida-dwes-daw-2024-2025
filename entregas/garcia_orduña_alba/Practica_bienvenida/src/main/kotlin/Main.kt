package org.example

import com.github.michaelbull.result.mapBoth
import di.appModule
import org.example.tenista.services.TenistaService
import org.koin.core.annotation.KoinExperimentalAPI

import org.koin.core.context.startKoin
import org.koin.fileProperties
import org.koin.java.KoinJavaComponent.inject
import java.nio.file.Files
import kotlin.io.path.Path

@OptIn(KoinExperimentalAPI::class)
fun main() {

    startKoin {
        printLogger()
        fileProperties("/config.properties")
        modules(appModule)
    }

    val app = tenistaApp()
    app.run()
}

class tenistaApp : KoinComponent {
    fun run() {
        val service: TenistaService by inject()

        val fileTenistasCsv = Path("src","main","resources","tenistas.csv").toFile()


        service.loadCsv(fileTenistasCsv).mapBoth(
            success = {
                Files.createDirectories(Path("data"))

                val fileJson = Path("data","tenistas.json").toFile()
                service.storeJson(fileJson,it)
            }
            , failure = {
                println("$it")
            }
        )
        val fileTenistasJson = Path("data","tenistas.json").toFile()
        service.loadJson(fileTenistasJson).mapBoth(
            success = {
                Files.createDirectories(Path("data"))

                val fileJson = Path("data","tenistas.csv").toFile()
                service.storeCsv(fileJson,it)
            }
            , failure = {
                println("$it")
            }
        )

        println(service.obtenerTenistaConMasRango())

    }
}

package app

import config.Config
import database.SqlDeLightManager
import tenistas.cache.CacheTenistaImpl
import tenistas.models.Tenista
import tenistas.repositories.TenistasRepositoryImpl
import tenistas.servicies.TenistasServiceImpl
import tenistas.storage.TenistaStorageCSV
import tenistas.storage.TenistasStorageExport
import tenistas.storage.TenistasStorageJSON
import tenistas.storage.TenistasStorageXML
import java.io.File


class TenistasApp {
    fun run(args:Array<String>): List<Tenista> {

        var storageExport:TenistasStorageExport = TenistasStorageJSON()
        if (args.size > 1){
            when{
                args[1].contains(".csv") -> storageExport = TenistaStorageCSV()
                args[1].contains(".json") -> storageExport = TenistasStorageJSON()
                args[1].contains(".xml") -> storageExport = TenistasStorageXML()
                else -> TenistasStorageJSON()
            }
        }

        val service = TenistasServiceImpl (
            repository = TenistasRepositoryImpl(
                SqlDeLightManager(
                    databaseUrl = Config.databaseUrl,
                )
            ),
            cache = CacheTenistaImpl(Config.cacheSize),
            store = TenistaStorageCSV(),
            export =storageExport
        )
        val lista=service.import(File(args[0]))

        val ficheroSalida = if (args.size == 2) {
            args[1]
        } else {
            "torneo_tenis.json"
        }

        val archivo = if (File(ficheroSalida).isAbsolute) {
            File(ficheroSalida)
        } else {
            File(System.getProperty("user.dir"), ficheroSalida)
        }
        archivo.parentFile?.let { parentDir ->
            if (!parentDir.exists()) {
                parentDir.mkdirs()
            }
        }
        if (!archivo.exists()) {
            archivo.createNewFile()
        }
        service.export(archivo,lista.value)
        return lista.value
    }
}
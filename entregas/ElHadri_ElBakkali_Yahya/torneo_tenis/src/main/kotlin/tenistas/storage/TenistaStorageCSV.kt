package tenistas.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import tenistas.dto.TenistaDto
import tenistas.errors.TenistasError
import tenistas.mappers.toTenista
import tenistas.models.Tenista
import java.io.File
private val logger= logging()
class TenistaStorageCSV:TenistaStorageStore,TenistasStorageExport {
    override fun load(file: File): Result<List<Tenista>, TenistasError> {
        logger.debug { "Carganado tenistas desde fichero Csv" }
        return try {
            Ok(
                if (comprobarFile(file)){
                    file.reader().readLines().drop(1)
                        .map {
                            separar(it)
                        }
                } else {
                    file.reader().readLines()
                        .map {
                           separar(it)
                        }
                }
            )

        }catch (e: Exception){
            logger.error { "Error al cargar el fichero csv " }
            Err((TenistasError.FileError("Error al leer el fichero csv")))
        }
    }
    private fun comprobarFile(file:File):Boolean{
        val lines= file.reader().readLines()
        return lines[0].contains("id")
    }

    private fun separar(it:String):Tenista{
        val data = it.split(",")
        return TenistaDto(
            id = data[0],
            nombre = data[1],
            pais = data[2],
            altura = data[3].toInt(),
            peso = data[4].toInt(),
            puntos = data[5].toInt(),
            mano = data[6],
            fechaNacimiento = data[7]
        ).toTenista()
    }


    override fun store(file: File, lista: List<Tenista>): Result<Long, TenistasError> {
        logger.debug { "Guardando tenistas en fichero CSV" }

        return try {

            file.writer().use { writer ->

                writer.write("id,nombre,pais,altura,peso,puntos,mano,fechaNacimiento\n")

                lista.forEach { tenista ->
                    writer.write("${tenista.id},${tenista.nombre},${tenista.pais},${tenista.altura},${tenista.peso},${tenista.puntos},${tenista.mano},${tenista.fechaNacimiento}\n")
                }
            }
            logger.info { "Tenistas guardados correctamente en el archivo CSV" }
            Ok(lista.size.toLong())

        } catch (e: Exception) {
            logger.error { "Error al guardar el archivo CSV: ${e.message}" }
            Err(TenistasError.FileError("Error al escribir en el archivo CSV"))
        }
    }
}
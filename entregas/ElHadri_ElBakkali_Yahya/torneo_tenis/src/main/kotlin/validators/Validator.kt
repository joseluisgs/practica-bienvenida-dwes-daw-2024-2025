package validators

import java.io.File
import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Paths

class Validator {


    fun validatorArgsEntrada(entrada: String):String{
        if (!validarEntrada(entrada)) return "El archivo de entrada debe ser un .csv"
        if (!validarFichero(entrada)) return "Debe de ser un archivo existente"
        if (!validarContenido(entrada)) return "El archivo no puede estar vacio"
        else return "Ok"
    }
    private fun validarEntrada(entrada: String): Boolean {
        return entrada.contains(".csv")
    }
    private fun validarFichero(fichero:String):Boolean{
        return try {
            Files.exists(Paths.get(fichero))
        }catch (e:InvalidPathException){
            false
        }
    }
    private fun validarContenido(fichero: String): Boolean {
        return File(fichero).length() > 0
    }



    fun validatorArgsSalida(salida:String):String{
        if (salida.isEmpty()) return "Ok"
        if (!validarSalida(salida)) return "El archivo de salida debe ser .json / .csv / .xml"
        else return "Ok"
    }
    private fun validarSalida(salida: String): Boolean {
        return salida.contains(".json") || salida.contains(".csv") || salida.contains(".xml")
    }
}
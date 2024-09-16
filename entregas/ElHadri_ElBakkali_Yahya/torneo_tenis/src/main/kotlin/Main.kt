import app.TenistasApp
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import tenistas.models.Tenista

import validators.Validator


val terminal= Terminal()

fun main(args:Array<String>) {

    val validador = Validator()
    val app=TenistasApp()
    var lista:List<Tenista> = listOf()
    when(args.size){
        0-> terminal.println(TextColors.red("Debes tener al menos un argumento"))
        1->{
            if (validador.validatorArgsEntrada(args[0]) == "Ok"){
                lista=app.run(args)
            }
        }
        2->{
            if (validador.validatorArgsEntrada(args[0]) == "Ok" && (
                        validador.validatorArgsSalida(args[1]) == "Ok")){
                lista=app.run(args)
            }
        }
    }
    if(args.size > 0 &&validador.validatorArgsEntrada(args[0]) != "Ok"){
       terminal.println(TextColors.red(validador.validatorArgsEntrada(args[0])))
    }else if(args.size==2 && validador.validatorArgsSalida(args[1]) != "Ok"){
       terminal.println(TextColors.red(validador.validatorArgsSalida(args[1])))
    }
    if (lista.size>0){
        terminal.println(TextColors.blue("Consultas"))
        terminal.println(TextColors.yellow("Tenistas ordenados con ranking, es decir, por puntos de mayor a menor"))
        lista.sortedByDescending { it.puntos }.forEach { println(it.nombre+" "+it.puntos) }
        terminal.println(TextColors.yellow("media de altura de los tenistas"))
        println(lista.map { it.altura }.average())
        terminal.println(TextColors.yellow("media de peso de los tenistas"))
        println(lista.map { it.peso }.average())
        terminal.println(TextColors.yellow("tenista más alto"))
        println( lista.maxBy { it.altura }.nombre)
        terminal.println(TextColors.yellow("tenistas de España"))
        lista.filter { it.pais=="España" }.forEach { it.nombre }
        terminal.println(TextColors.yellow("tenistas agrupados por país"))
        lista.groupBy { it.pais }.forEach { (pais, tenistas) ->
            println("País: $pais")
            tenistas.forEach { println(" - ${it.nombre}\n") }
        }
        terminal.println(TextColors.yellow("número de tenistas agrupados por país y ordenados por puntos descendente"))
        lista.groupBy { it.pais }
            .mapValues {
                it.value.sortedByDescending { it.puntos }
            }
            .forEach { (pais, tenistas) ->
                println("País: $pais")
                tenistas.forEach { println("  - ${it.nombre} ~ ${it.puntos} puntos\n") }
            }

    }

}

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
    
}

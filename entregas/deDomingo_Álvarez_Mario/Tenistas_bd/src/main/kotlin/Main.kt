package org.example

fun main(args: Array<String>) {
    if (args.isNotEmpty() && args[0].endsWith(".csv")) {
        println("The input file is a CSV file.")
    } else {
        println("The input file is not a CSV file.")
        System.exit(1)
    }
    //consultas(tenistas.findAll())
}
/*fun consultas(tenistas: Array<Tenista>){
    // Implementar consultas a la base de datos
    println("Consultas realizadas")
    // tenistas ordenados con ranking, es decir, por puntos de mayor a menor
    // a la lista de Tenistas los ordenamos por rangking

    // media de altura de los tenistas
    //  Media de peso de los tenistas
    //  tenista más alto
    //  tenistas de españa
    //  tenistas agrupados por país
    //  número de tenistas agrupados por país y ordenados por puntos descendentes
    //  numero de tenistas agrupados por mano dominante y puntuación media de ellos
    //  puntuación total de los tenistas agrupados por país
    //  país con más puntuación total
    //  tenista con mejor ranking de España.
}*/
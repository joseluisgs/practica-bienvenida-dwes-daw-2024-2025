package org.example

import org.example.di.*
import org.koin.core.context.GlobalContext.startKoin
import org.koin.fileProperties
import org.koin.test.check.checkKoinModules

fun main(args: Array<String>) {
    checkMyModules()

    startKoin {
        printLogger()
        fileProperties("/config.properties")
        modules(listOf(tenistasModule))
    }

    val app = TorneoTenistasApp()
    app.run(args)
}

fun checkMyModules() {
    checkKoinModules(listOf())
}
package org.example.di

import org.example.cache.CacheImpl
import org.example.config.Config
import org.example.database.service.SqlDeLightManager
import org.example.tenistas.repositories.TenistaRepository
import org.example.tenistas.repositories.TenistaRepositoryImpl
import org.example.tenistas.services.TenistaService
import org.example.tenistas.services.TenistaServiceImpl
import org.example.tenistas.storage.genericStorage.TenistaStorages
import org.example.tenistas.storage.storageCSV.StorageCSV
import org.example.tenistas.storage.storageJSON.StorageJSON
import org.example.tenistas.storage.storageXML.StorageXML
import org.koin.dsl.module

val tenistasModule = module {
    single { SqlDeLightManager(databaseUrl = getProperty("database.url", "jdbc:sqlite:tenistas.db")) }

    single { Config }

    single<TenistaRepository> { TenistaRepositoryImpl(get()) }

    single<TenistaService> { TenistaServiceImpl(get(), get()) }

    single { CacheImpl(5) }

    single { StorageCSV() }
    single { StorageXML() }
    single { StorageJSON() }

    single { TenistaStorages(get(), get(), get()) }

}
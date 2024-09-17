package di

import cache.CacheImpl
import config.Config
import database.SqlDelightManager
import org.koin.dsl.module
import storage.StorageCsv
import storage.StorageJson
import storage.StorageXml
import tenistas.repositories.TenistaRepository
import tenistas.repositories.TenistaRepositoryImpl
import tenistas.services.TenistaService
import tenistas.services.TenistaServiceImpl
import tenistas.validators.TenistaValidator

/**
 * Módulo de inyección de dependencias para Tenista utilizando Koin.
 *
 * @author Natalia González
 * @version 1.0
 */
val tenistaModule = module {

    /**
     * Proporciona una instancia de [SqlDelightManager] como singleton, que maneja la base de datos.
     */
    single { SqlDelightManager(get()) }

    /**
     * Proporciona una instancia singleton de [Config] que gestiona la configuración de la aplicación.
     */
    single { Config }

    /**
     * Proporciona una implementación de [TenistaRepository], utilizando [TenistaRepositoryImpl].
     */
    single<TenistaRepository> { TenistaRepositoryImpl(get()) }

    /**
     * Proporciona una implementación de [TenistaService], utilizando [TenistaServiceImpl].
     * Inyecta [TenistaRepository], [CacheImpl], y otros servicios de almacenamiento.
     */
    single<TenistaService> { TenistaServiceImpl(get(), get(), get()) }

    /**
     * Proporciona una instancia de [CacheImpl] con un tamaño máximo de 5 elementos.
     */
    single { CacheImpl(5) }

    /**
     * Proporciona instancias de diferentes tipos de almacenamiento: CSV, JSON y XML.
     * Estas instancias se pueden usar según sea necesario en la aplicación.
     */
    single { StorageCsv() }
    single { StorageJson() }
    single { StorageXml() }

    /**
     * Proporciona una instancia singleton de [TenistaValidator] que valida los datos de los tenistas.
     */
    single { TenistaValidator() }
}
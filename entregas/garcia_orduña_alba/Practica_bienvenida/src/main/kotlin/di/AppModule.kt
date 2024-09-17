package di
import cache.CacheTenistaImpl
import config.Config
import database.SqlDelightManager
import org.example.persona.repositories.TenistaRepository
import org.example.persona.repositories.TenistaRepositoryImpl
import org.example.tenista.services.TenistaService
import org.koin.dsl.module

import tenista.storage.StorageCsv
import tenista.storage.StorageJson

val appModule = module{

    single<CacheTenistaImpl> {CacheTenistaImpl(5)}
    single<TenistaRepository>{ TenistaRepositoryImpl(get()) }
    single<TenistaService>{TenistaServiceImpl(get(),get(),get(),get())}
    single<StorageCsv>{StorageCsv()}
    single<StorageJson>{StorageJson()}

    single <Config>{Config}

    single<SqlDelightManager> { SqlDelightManager(get()) }


}
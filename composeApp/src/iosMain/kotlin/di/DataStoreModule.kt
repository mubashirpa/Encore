package di

import core.utils.dataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStoreModule: Module = module {
    single { dataStore() }
}
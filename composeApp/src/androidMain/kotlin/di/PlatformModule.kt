package di

import core.utils.AndroidUrlLauncher
import core.utils.UrlLauncher
import core.utils.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { dataStore(androidContext()) }
    single<UrlLauncher> { AndroidUrlLauncher(androidContext()) }
}
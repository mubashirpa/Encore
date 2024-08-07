package di

import core.utils.IOSUrlLauncher
import core.utils.UrlLauncher
import core.utils.dataStore
import org.koin.core.module.Module
import org.koin.dsl.module
import player.MediaPlayerFactory

actual val platformModule: Module =
    module {
        single { dataStore() }
        single<UrlLauncher> { IOSUrlLauncher() }
        single { MediaPlayerFactory() }
    }

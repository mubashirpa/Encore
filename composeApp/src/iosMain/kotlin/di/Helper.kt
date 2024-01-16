package di

import org.koin.core.context.startKoin

// TODO("Move to core.utils")

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
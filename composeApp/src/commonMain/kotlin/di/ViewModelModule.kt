package di

import org.koin.dsl.module
import presentation.home.HomeViewModel

val viewModelModule = module {
    factory {
        HomeViewModel(get(), get(), get())
    }
}
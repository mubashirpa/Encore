package di

import org.koin.dsl.module
import presentation.home.HomeViewModel

val viewModelModule = module {
    factory {
        HomeViewModel(
            requestAccessTokenUseCase = get(),
            getAccessTokenUseCase = get(),
            getCategoriesUseCase = get(),
            getFeaturedPlaylistsUseCase = get(),
            getUsersTopItemsUseCase = get(),
            requestUserAuthorizationUseCase = get(),
            urlLauncher = get()
        )
    }
}
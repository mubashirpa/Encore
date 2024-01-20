package di

import org.koin.dsl.module
import presentation.MainViewModel
import presentation.home.HomeViewModel

val viewModelModule = module {
    factory {
        HomeViewModel(
            getAccessTokenUseCase = get(),
            getFeaturedPlaylistsUseCase = get(),
            getUsersTopTracksUseCase = get()
        )
    }
    factory {
        MainViewModel(
            requestUserAuthorizationUseCase = get(),
            requestAuthAccessTokenUseCase = get(),
            getAccessTokenUseCase = get(),
            refreshTokenUseCase = get(),
            urlLauncher = get()
        )
    }
}
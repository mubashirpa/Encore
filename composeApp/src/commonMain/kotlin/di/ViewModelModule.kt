package di

import org.koin.dsl.module
import presentation.MainViewModel
import presentation.home.HomeViewModel

val viewModelModule = module {
    factory {
        HomeViewModel(
            getAccessTokenUseCase = get(),
            getCurrentUsersProfileUseCase = get(),
            getFeaturedPlaylistsUseCase = get(),
            getUsersTopTracksUseCase = get()
        )
    }
    factory {
        MainViewModel(
            getAccessTokenUseCase = get(),
            refreshTokenUseCase = get(),
            requestAuthAccessTokenUseCase = get(),
            requestUserAuthorizationUseCase = get(),
            urlLauncher = get()
        )
    }
}
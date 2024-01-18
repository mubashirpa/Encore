package di

import org.koin.dsl.module
import presentation.home.HomeViewModel

val viewModelModule = module {
    factory {
        HomeViewModel(
            requestAuthAccessTokenUseCase = get(),
            getAccessTokenUseCase = get(),
            getFeaturedPlaylistsUseCase = get(),
            getUsersTopTracksUseCase = get(),
            requestUserAuthorizationUseCase = get(),
            urlLauncher = get()
        )
    }
}
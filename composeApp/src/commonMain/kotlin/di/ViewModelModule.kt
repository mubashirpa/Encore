package di

import org.koin.dsl.module
import presentation.MainViewModel
import presentation.home.HomeViewModel
import presentation.home_container.HomeContainerViewModel
import presentation.search.SearchViewModel

val viewModelModule = module {
    factory {
        HomeContainerViewModel(
            getAccessTokenUseCase = get(),
            getCurrentUsersProfileUseCase = get()
        )
    }
    factory {
        HomeViewModel(
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
    factory {
        SearchViewModel(
            getCategoriesUseCase = get()
        )
    }
}
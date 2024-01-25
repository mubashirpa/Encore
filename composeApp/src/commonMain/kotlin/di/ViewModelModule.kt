package di

import org.koin.dsl.module
import presentation.MainViewModel
import presentation.home.HomeViewModel
import presentation.search.SearchViewModel

val viewModelModule =
    module {
        factory {
            HomeViewModel(
                getFeaturedPlaylistsUseCase = get(),
                getLaunchDataUseCase = get(),
                getUsersTopTracksUseCase = get(),
            )
        }
        factory {
            MainViewModel(
                getAccessTokenUseCase = get(),
                refreshTokenUseCase = get(),
                requestAuthAccessTokenUseCase = get(),
                requestUserAuthorizationUseCase = get(),
                urlLauncher = get(),
            )
        }
        factory {
            SearchViewModel(
                getCategoriesUseCase = get(),
            )
        }
    }

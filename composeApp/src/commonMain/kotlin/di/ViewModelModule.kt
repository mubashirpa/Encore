package di

import org.koin.dsl.module
import presentation.MainViewModel
import presentation.home.HomeViewModel
import presentation.library.LibraryViewModel
import presentation.player.PlayerViewModel
import presentation.playlist.PlaylistViewModel
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
            LibraryViewModel(
                getCurrentUsersPlaylistsUseCase = get(),
                getFollowedArtistsUseCase = get(),
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
            PlayerViewModel(
                getTrackUseCase = get(),
            )
        }
        factory {
            PlaylistViewModel(
                getPlaylistItemsUseCase = get(),
            )
        }
        factory {
            SearchViewModel(
                getCategoriesUseCase = get(),
                searchForItemUseCase = get(),
            )
        }
    }

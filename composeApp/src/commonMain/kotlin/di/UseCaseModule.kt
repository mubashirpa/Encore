package di

import domain.usecase.saavn.launchData.GetLaunchDataUseCase
import domain.usecase.spotify.RequestUserAuthorizationUseCase
import domain.usecase.spotify.accessToken.GetAccessTokenUseCase
import domain.usecase.spotify.accessToken.RefreshTokenUseCase
import domain.usecase.spotify.accessToken.RequestAuthAccessTokenUseCase
import domain.usecase.spotify.accessToken.RequestCredentialAccessTokenUseCase
import domain.usecase.spotify.category.GetCategoriesUseCase
import domain.usecase.spotify.playlists.GetFeaturedPlaylistsUseCase
import domain.usecase.spotify.search.SearchForItemUseCase
import domain.usecase.spotify.users.GetCurrentUsersProfileUseCase
import domain.usecase.spotify.users.GetUsersTopTracksUseCase
import org.koin.dsl.module

val useCaseModule =
    module {
        single { GetAccessTokenUseCase(get()) }
        single { GetCategoriesUseCase(get()) }
        single { GetCurrentUsersProfileUseCase(get()) }
        single { GetFeaturedPlaylistsUseCase(get()) }
        single { GetLaunchDataUseCase(get()) }
        single { GetUsersTopTracksUseCase(get()) }
        single { RefreshTokenUseCase(get(), get()) }
        single { RequestAuthAccessTokenUseCase(get(), get()) }
        single { RequestCredentialAccessTokenUseCase(get(), get()) }
        single { RequestUserAuthorizationUseCase(get()) }
        single { SearchForItemUseCase(get()) }
    }

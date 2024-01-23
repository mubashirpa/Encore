package di

import domain.usecase.spotify.RequestUserAuthorizationUseCase
import domain.usecase.spotify.access_token.GetAccessTokenUseCase
import domain.usecase.spotify.access_token.RefreshTokenUseCase
import domain.usecase.spotify.access_token.RequestAuthAccessTokenUseCase
import domain.usecase.spotify.access_token.RequestCredentialAccessTokenUseCase
import domain.usecase.spotify.category.GetCategoriesUseCase
import domain.usecase.spotify.playlists.GetFeaturedPlaylistsUseCase
import domain.usecase.spotify.users.GetCurrentUsersProfileUseCase
import domain.usecase.spotify.users.GetUsersTopTracksUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetAccessTokenUseCase(get()) }
    single { GetCategoriesUseCase(get()) }
    single { GetCurrentUsersProfileUseCase(get()) }
    single { GetFeaturedPlaylistsUseCase(get()) }
    single { GetUsersTopTracksUseCase(get()) }
    single { RefreshTokenUseCase(get(), get()) }
    single { RequestAuthAccessTokenUseCase(get(), get()) }
    single { RequestCredentialAccessTokenUseCase(get(), get()) }
    single { RequestUserAuthorizationUseCase(get()) }
}
package di

import domain.usecase.spotify.RequestUserAuthorizationUseCase
import domain.usecase.spotify.access_token.GetAccessTokenUseCase
import domain.usecase.spotify.access_token.RequestAccessTokenUseCase
import domain.usecase.spotify.categories.GetCategoriesUseCase
import domain.usecase.spotify.playlists.GetFeaturedPlaylistsUseCase
import domain.usecase.spotify.users.GetUsersTopItemsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCategoriesUseCase(get()) }
    single { RequestAccessTokenUseCase(get(), get()) }
    single { GetAccessTokenUseCase(get()) }
    single { GetFeaturedPlaylistsUseCase(get()) }
    single { GetUsersTopItemsUseCase(get()) }
    single { RequestUserAuthorizationUseCase(get()) }
}
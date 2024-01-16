package di

import domain.usecase.spotify.access_token.GetAccessTokenUseCase
import domain.usecase.spotify.access_token.RequestAccessTokenUseCase
import domain.usecase.spotify.categories.GetCategoriesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCategoriesUseCase(get()) }
    single { RequestAccessTokenUseCase(get(), get()) }
    single { GetAccessTokenUseCase(get()) }
}
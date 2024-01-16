package di

import data.repository.SpotifyRepositoryImpl
import data.repository.UserPreferencesRepositoryImpl
import domain.repository.SpotifyRepository
import domain.repository.UserPreferencesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SpotifyRepository> { SpotifyRepositoryImpl(get()) }
    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(get()) }
}
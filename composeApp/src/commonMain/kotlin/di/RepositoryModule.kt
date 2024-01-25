package di

import data.repository.SaavnRepositoryImpl
import data.repository.SpotifyRepositoryImpl
import data.repository.UserPreferencesRepositoryImpl
import domain.repository.SaavnRepository
import domain.repository.SpotifyRepository
import domain.repository.UserPreferencesRepository
import org.koin.dsl.module

val repositoryModule =
    module {
        single<SaavnRepository> { SaavnRepositoryImpl(get()) }
        single<SpotifyRepository> { SpotifyRepositoryImpl(get()) }
        single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(get()) }
    }

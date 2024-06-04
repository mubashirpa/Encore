package di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.MainViewModel
import presentation.home.HomeViewModel
import presentation.library.LibraryViewModel
import presentation.player.PlayerViewModel
import presentation.playlist.PlaylistViewModel
import presentation.search.SearchViewModel

val viewModelModule =
    module {
        viewModelOf(::HomeViewModel)
        viewModelOf(::LibraryViewModel)
        viewModelOf(::MainViewModel)
        viewModelOf(::PlayerViewModel)
        viewModelOf(::PlaylistViewModel)
        viewModelOf(::SearchViewModel)
    }

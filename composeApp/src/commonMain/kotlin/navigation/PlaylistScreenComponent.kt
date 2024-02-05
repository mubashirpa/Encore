package navigation

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.playlist.PlaylistViewModel

interface PlaylistScreenComponent {
    val viewModel: PlaylistViewModel
    val playlistId: String
}

class DefaultPlaylistScreenComponent(
    componentContext: ComponentContext,
    override val playlistId: String,
) : PlaylistScreenComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: PlaylistViewModel by inject()
}

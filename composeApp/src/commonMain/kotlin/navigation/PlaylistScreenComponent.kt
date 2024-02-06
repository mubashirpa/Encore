package navigation

import com.arkivanov.decompose.ComponentContext
import domain.model.saavn.playlists.PlaylistItem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.playlist.PlaylistViewModel

interface PlaylistScreenComponent {
    val viewModel: PlaylistViewModel
    val playlistId: String

    fun onCloseClicked()

    fun onPlaylistItemClicked(playlistItem: PlaylistItem)
}

class DefaultPlaylistScreenComponent(
    componentContext: ComponentContext,
    override val playlistId: String,
    private val onFinished: () -> Unit,
    private val onPlayTrack: (playlistItem: PlaylistItem) -> Unit,
) : PlaylistScreenComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: PlaylistViewModel by inject()

    override fun onCloseClicked() {
        onFinished()
    }

    override fun onPlaylistItemClicked(playlistItem: PlaylistItem) {
        onPlayTrack(playlistItem)
    }
}

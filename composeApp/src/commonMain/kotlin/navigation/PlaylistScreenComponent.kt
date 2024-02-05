package navigation

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.playlist.PlaylistViewModel

interface PlaylistScreenComponent {
    val viewModel: PlaylistViewModel
    val playlistId: String

    fun onBackClicked()
}

class DefaultPlaylistScreenComponent(
    componentContext: ComponentContext,
    override val playlistId: String,
    private val onFinished: () -> Unit,
) : PlaylistScreenComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: PlaylistViewModel by inject()

    override fun onBackClicked() {
        onFinished()
    }
}

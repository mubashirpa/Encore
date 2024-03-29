package navigation

import com.arkivanov.decompose.ComponentContext
import domain.model.tracks.Track
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.player.PlayerViewModel

interface PlayerComponent {
    val viewModel: PlayerViewModel
    val tracks: List<Track>

    fun onDismissClicked()
}

class DefaultPlayerComponent(
    private val componentContext: ComponentContext,
    override val tracks: List<Track>,
    private val onDismissed: () -> Unit,
) : PlayerComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: PlayerViewModel by inject()

    override fun onDismissClicked() {
        onDismissed()
    }
}

package navigation

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.home.HomeViewModel

interface HomeScreenComponent {
    val viewModel: HomeViewModel
}

class DefaultHomeScreenComponent(
    componentContext: ComponentContext,
) : HomeScreenComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: HomeViewModel by inject()
}

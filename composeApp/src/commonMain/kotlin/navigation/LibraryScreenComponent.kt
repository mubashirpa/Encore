package navigation

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.library.LibraryViewModel

interface LibraryScreenComponent {
    val viewModel: LibraryViewModel
}

class DefaultLibraryScreenComponent(
    componentContext: ComponentContext,
) : LibraryScreenComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: LibraryViewModel by inject()
}

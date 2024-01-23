package navigation

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.search.SearchViewModel

interface SearchScreenComponent {

    val viewModel: SearchViewModel
}

class DefaultSearchScreenComponent(
    componentContext: ComponentContext
) : SearchScreenComponent, ComponentContext by componentContext, KoinComponent {

    override val viewModel: SearchViewModel by inject()
}
package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.search.SearchUiEvent
import presentation.search.SearchViewModel

interface SearchScreenComponent {
    val viewModel: SearchViewModel

    fun updateBackCallback(isEnabled: Boolean)
}

class DefaultSearchScreenComponent(
    componentContext: ComponentContext,
) : SearchScreenComponent, ComponentContext by componentContext, KoinComponent {
    override val viewModel: SearchViewModel by inject()

    private val backCallback =
        BackCallback(isEnabled = false) {
            viewModel.onEvent(SearchUiEvent.OnSearchBarActiveChange(false))
        }

    init {
        backHandler.register(backCallback)
    }

    override fun updateBackCallback(isEnabled: Boolean) {
        backCallback.isEnabled = isEnabled
    }
}

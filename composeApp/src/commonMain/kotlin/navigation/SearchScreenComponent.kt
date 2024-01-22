package navigation

import com.arkivanov.decompose.ComponentContext

interface SearchScreenComponent

class DefaultSearchScreenComponent(
    componentContext: ComponentContext
) : SearchScreenComponent, ComponentContext by componentContext
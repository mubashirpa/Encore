package navigation

import com.arkivanov.decompose.ComponentContext

interface LibraryScreenComponent

class DefaultLibraryScreenComponent(
    componentContext: ComponentContext,
) : LibraryScreenComponent, ComponentContext by componentContext

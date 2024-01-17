package navigation

import com.arkivanov.decompose.ComponentContext

interface HomeScreenComponent {

    val authorizationCode: String?

    fun onCloseClicked()
}

class DefaultHomeScreenComponent(
    componentContext: ComponentContext,
    authCode: String?,
    private val onFinished: () -> Unit
) : HomeScreenComponent, ComponentContext by componentContext {

    override val authorizationCode: String? = authCode

    override fun onCloseClicked() {
        onFinished()
    }
}
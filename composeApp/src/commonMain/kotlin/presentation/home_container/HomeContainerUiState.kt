package presentation.home_container

import domain.model.spotify.users.profile.User

data class HomeContainerUiState(
    val accessToken: String = "",
    val currentUsersProfile: User? = null
)
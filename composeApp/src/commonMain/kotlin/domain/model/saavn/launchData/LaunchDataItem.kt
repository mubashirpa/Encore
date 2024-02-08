package domain.model.saavn.launchData

data class LaunchDataItem(
    val id: String? = null,
    val image: String? = null,
    val subtitle: String? = null,
    val title: String? = null,
) {
    val formattedTitle =
        title?.replace("&quot;", "\"")?.replace("&#039;", "'")?.replace("&amp;", "&")
}

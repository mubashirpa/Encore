package domain.repository

import data.remote.dto.saavn.LaunchDataDto

interface SaavnRepository {
    suspend fun getLaunchData(languages: List<String> = listOf("english")): LaunchDataDto
}

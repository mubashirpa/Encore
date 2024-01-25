package data.repository

import core.Saavn
import data.remote.dto.saavn.LaunchDataDto
import domain.repository.SaavnRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json

class SaavnRepositoryImpl(
    private val httpClient: HttpClient,
) : SaavnRepository {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getLaunchData(languages: List<String>): LaunchDataDto {
        val l = languages.joinToString("%2C")
        val response =
            httpClient.get("${Saavn.API_BASE_URL}${Saavn.ENDPOINT_LAUNCH_DATA}") {
                headers {
                    append(HttpHeaders.Cookie, "L=$l")
                    append(HttpHeaders.Accept, "*/*")
                }
            }
        return json.decodeFromString(response.body())
    }
}

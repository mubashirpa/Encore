package domain.usecase.saavn.launchData

import core.Result
import data.mapper.toLaunchData
import domain.model.saavn.LaunchData
import domain.repository.SaavnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLaunchDataUseCase(private val saavnRepository: SaavnRepository) {
    operator fun invoke(languages: List<String> = listOf("english")): Flow<Result<LaunchData>> =
        flow {
            try {
                emit(Result.Loading())
                val launchData = saavnRepository.getLaunchData(languages).toLaunchData()
                emit(Result.Success(launchData))
            } catch (e: Exception) {
                emit(Result.Error(message = e.message.toString()))
            }
        }
}

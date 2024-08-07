package data.repository

import data.network.di.YouBikeApi
import data.network.dto.YouBikeDataBean
import domain.common.ApiResult
import domain.common.fetch
import domain.repository.YouBikeApiRepository
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow

class YouBikeApiRepositoryImpl(
    private val api: YouBikeApi
): YouBikeApiRepository {

    override suspend fun fetchYouBikeData(): Flow<ApiResult<List<YouBikeDataBean>>> =
        api.httpClient.fetch {
            url(api.baseUrl)
            method = HttpMethod.Get
        }
}
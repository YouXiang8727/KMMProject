package domain.repository

import data.network.dto.YouBikeDataBean
import domain.common.ApiResult
import kotlinx.coroutines.flow.Flow

interface YouBikeApiRepository {
    suspend fun fetchYouBikeData(): Flow<ApiResult<List<YouBikeDataBean>>>
}
package domain.common

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class ApiResult<out T>(
    val data: T? = null,
    val throwable: Throwable? = null
) {
    data class Success<out T>(private val _data: T) : ApiResult<T>(_data, null)
    data class Error<out T>(private val _throwable: Throwable) : ApiResult<T>(null, _throwable)
    data object Loading: ApiResult<Nothing>()
}

suspend inline fun <reified T> HttpClient.fetch(
    crossinline block: HttpRequestBuilder.() -> Unit
): Flow<ApiResult<T>> =
    flow {
        emit(ApiResult.Loading)
        val result: ApiResult<T> = try {
            val response = request(block)
            if (response.status == HttpStatusCode.OK) {
                ApiResult.Success(response.body())
            }else {
                ApiResult.Error(
                    Exception(
                        "${response.status.value} ${response.bodyAsText()}"
                    )
                )
            }
        }catch (e: Exception) {
            ApiResult.Error(e)
        }
        emit(result)
    }

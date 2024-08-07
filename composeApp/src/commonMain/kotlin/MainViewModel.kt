import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.network.dto.YouBikeDataBean
import domain.common.ApiResult
import domain.repository.YouBikeApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel: ViewModel(), KoinComponent {
    private val youBikeApiRepository: YouBikeApiRepository by inject()

    private val _apiResultFlow: MutableStateFlow<ApiResult<List<YouBikeDataBean>>> = MutableStateFlow(ApiResult.Loading)
    val apiResultFlow = _apiResultFlow.asStateFlow()

    init {
        fetchYouBikeData()
    }

    fun fetchYouBikeData() {
        viewModelScope.launch {
            youBikeApiRepository.fetchYouBikeData().collect { result ->
                _apiResultFlow.emit(result)
            }
        }
    }
}
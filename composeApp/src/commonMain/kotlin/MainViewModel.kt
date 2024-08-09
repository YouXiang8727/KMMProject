import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.network.dto.YouBikeDataBean
import domain.common.ApiResult
import domain.model.Language
import domain.model.YouBikeData
import domain.repository.YouBikeApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel: ViewModel(), KoinComponent {
    private val youBikeApiRepository: YouBikeApiRepository by inject()

    private val _apiResultFlow: MutableStateFlow<ApiResult<List<YouBikeDataBean>>> = MutableStateFlow(ApiResult.Loading)
    private val _youBikeDataMap: SnapshotStateMap<String, YouBikeDataBean> = mutableStateMapOf()

    val apiResultFlow = _apiResultFlow.asStateFlow()
    val youBikeDataMap = _youBikeDataMap

    private val _currentLanguage: MutableStateFlow<Language> = MutableStateFlow(Language.CN)
    val currentLanguage = _currentLanguage.asStateFlow()

    init {
        collectApiResultFlow()
        fetchYouBikeData()
    }

    private fun collectApiResultFlow() {
        viewModelScope.launch {
            apiResultFlow.collect { result ->
                if (result is ApiResult.Success) {
                    result.data?.let { data ->
                        _youBikeDataMap.putAll(
                            data.associate {
                                it.sno to it
                            }
                        )
                    }
                }
            }
        }
    }

    fun fetchYouBikeData() {
        viewModelScope.launch {
            youBikeApiRepository.fetchYouBikeData().collect { result ->
                _apiResultFlow.emit(result)
            }
        }
    }

    fun switchCurrentLanguage() {
        _currentLanguage.value = when (_currentLanguage.value) {
            Language.CN -> Language.EN
            Language.EN -> Language.CN
        }
    }
}
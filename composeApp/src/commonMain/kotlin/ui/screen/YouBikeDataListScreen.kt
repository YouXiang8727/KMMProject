package ui.screen

import MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.common.ApiResult
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import ui.item.FetchYouBikeDataButton
import ui.item.YouBikeDataItem
import ui.item.YouBikeDataListScreenTopBar

@Composable
fun YouBikeDataListScreen() {
    val viewModel: MainViewModel = koinViewModel<MainViewModel>()
    val result = viewModel.apiResultFlow.collectAsState()
    val dataMap = viewModel.youBikeDataMap
    val currentLanguage = viewModel.currentLanguage.collectAsState()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            YouBikeDataListScreenTopBar()
        },
        floatingActionButton = {
            FetchYouBikeDataButton()
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LazyColumn {
                dataMap.values.toList().let { list ->
                    items(list){ data ->
                        YouBikeDataItem(data.toYouBikeData(currentLanguage.value))
                    }
                }
            }

            if (result.value == ApiResult.Loading) {
                CircularProgressIndicator(
                    strokeWidth = 5.dp
                )
            }

            if (result.value is ApiResult.Error) {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = (result.value as ApiResult.Error).throwable?.message ?: ""
                    )
                }
            }
        }
    }
}
package ui.screen

import MainViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.common.ApiResult
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import ui.item.YouBikeDataItem

@Composable
fun YouBikeDataListScreen() {
    val viewModel: MainViewModel = koinViewModel<MainViewModel>()
    val result = viewModel.apiResultFlow.collectAsState()
    val dataMap = viewModel.youBikeDataMap

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.fetchYouBikeData()
                },
                modifier = Modifier,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LazyColumn {
                dataMap.values.toList().let { list ->
                    items(list){ data ->
                        YouBikeDataItem(data)
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
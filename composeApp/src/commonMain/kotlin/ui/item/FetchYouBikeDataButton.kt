package ui.item

import MainViewModel
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.common.ApiResult
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FetchYouBikeDataButton() {
    val viewModel: MainViewModel = koinViewModel()
    val apiResult = viewModel.apiResultFlow.collectAsState()

    val enabled = apiResult.value != ApiResult.Loading

    FloatingActionButton(
        onClick = {
            if (enabled) {
                viewModel.fetchYouBikeData()
            }
        },
        modifier = Modifier,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        backgroundColor = if (enabled) {
            Color.White
        }else {
            Color.Gray
        }
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null
        )
    }
}
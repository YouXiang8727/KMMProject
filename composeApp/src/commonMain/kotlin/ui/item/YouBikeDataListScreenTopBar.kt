package ui.item

import MainViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.model.Language
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun YouBikeDataListScreenTopBar() {
    val viewModel: MainViewModel = koinViewModel<MainViewModel>()
    val currentLanguage = viewModel.currentLanguage.collectAsState()

    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {},
            backgroundColor =  Color.White,
            navigationIcon = {
            },
            actions = {
                IconButton(
                    onClick = {
                        viewModel.switchCurrentLanguage()
                    }
                ) {
                    Text(
                        currentLanguage.value.value
                    )
                }
            }
        )
    }
}
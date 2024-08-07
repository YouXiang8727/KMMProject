import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import domain.common.ApiResult
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmmproject.composeapp.generated.resources.Res
import kmmproject.composeapp.generated.resources.compose_multiplatform
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(
                viewModelModule,
                youBikeApiModule
            )
        }
    ) {
        MaterialTheme {
            MainViewModelTest()
        }
    }
}


@Composable
fun MainViewModelTest() {
    val viewModel: MainViewModel = koinViewModel<MainViewModel>()

    val result = viewModel.apiResultFlow.collectAsState()

    Column {
        Button(
            onClick = {
                viewModel.fetchYouBikeData()
            },
            enabled = result.value != ApiResult.Loading
        ) {
            Text("fetch api data")
        }

        Text(result.value.toString())
    }
}
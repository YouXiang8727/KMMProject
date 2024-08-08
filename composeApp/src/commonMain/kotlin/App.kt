import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.KoinApplication
import ui.screen.YouBikeDataListScreen

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
            YouBikeDataListScreen()
        }
    }
}
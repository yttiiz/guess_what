import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun App(name: String) {
    MaterialTheme {
        Column(
        ) {
            Header(name)
            HomeBody()
        }
    }
}
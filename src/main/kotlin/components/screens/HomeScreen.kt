import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(name: String) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.background(Color(0xFF005403))) {
            Logo(name, width = 40.0, height = 40.0)
        }
    }
}
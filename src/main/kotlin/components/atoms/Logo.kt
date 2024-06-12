import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Logo(name: String, width: Double, height: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            contentDescription = "$name logo",
            painter = painterResource(resourcePath = "/drawable/icons/dt_logo.svg"),
            modifier = Modifier
                .width(width.dp)
                .height(height.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = name, color = Color.White)
    }
}
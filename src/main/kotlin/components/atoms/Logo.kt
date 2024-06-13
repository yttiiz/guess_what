import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import quiz.ui.theme.primaryForegroundColor

@Composable
fun Logo(name: String, width: Double, height: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            contentDescription = "$name logo",
            painter = painterResource(resourcePath = "/drawable/icons/yz_logo.svg"),
            modifier = Modifier
                .width(width.dp)
                .height(height.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = name, color = primaryForegroundColor)
    }
}
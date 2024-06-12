import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * The main `header` of the application.
 */
@Composable
fun Header(logoName: String): Unit {
    var text by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(20.dp),
    ) {
        Logo(
            name = logoName,
            width = 60.0,
            height = 70.6,
        )
        Column {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                onClick = {
                    text = "Hello Everybody"
                }) {
                Text("Login")
            }
        }
    }
}

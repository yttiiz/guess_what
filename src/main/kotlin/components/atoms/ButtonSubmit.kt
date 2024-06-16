import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.ui.theme.*

@Composable
fun ButtonSubmit(text: String, fetchData: () -> Unit) {
    Button(
        shape = RoundedCornerShape(5.dp),
        contentPadding = PaddingValues(20.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = secondaryBackgroundColor,
            contentColor = secondaryForegroundColor,
        ),
        onClick = { fetchData() },
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight(600)
        )
    }
}
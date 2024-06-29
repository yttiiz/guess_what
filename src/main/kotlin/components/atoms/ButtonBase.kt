package quiz.components.atoms

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.ui.theme.montserratBold

@Composable
fun ButtonBase(
    textContent: String,
    modifier: Modifier,
    colors: ButtonColors,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(5.dp),
        contentPadding = PaddingValues(20.dp),
        modifier = modifier,
        colors = colors,
        onClick = { onClick() },
    ) {
        Text(
            text = textContent,
            fontFamily = FontFamily(Font(montserratBold)),
            fontSize = 16.sp,
        )
    }
}
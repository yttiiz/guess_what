package quiz.components.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import quiz.ui.theme.neutralColor

@Composable
fun ButtonDismiss(
    textContent: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onClick: () -> Unit
) {
    ButtonBase(
        textContent = textContent,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = neutralColor,
            contentColor = Color.White,
        ),
        onClick = onClick,
    )
}
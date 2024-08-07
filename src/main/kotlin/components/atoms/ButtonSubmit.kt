package quiz.components.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import quiz.ui.theme.secondaryBackgroundColor
import quiz.ui.theme.secondaryForegroundColor

@Composable
fun ButtonSubmit(
    textContent: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onClick: () -> Unit
) {
    ButtonBase(
        textContent = textContent,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = secondaryBackgroundColor,
            contentColor = secondaryForegroundColor,
        ),
        onClick = onClick,
    )
}


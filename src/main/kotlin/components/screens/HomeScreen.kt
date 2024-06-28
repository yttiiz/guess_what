package quiz.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import quiz.ui.theme.neutralColor
import quiz.ui.theme.primaryBackgroundColor
import quiz.ui.theme.primaryForegroundColor

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.background(neutralColor)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                textAlign = TextAlign.Center,
                color = primaryBackgroundColor,
                text = "Bienvenue au Quiz Game",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
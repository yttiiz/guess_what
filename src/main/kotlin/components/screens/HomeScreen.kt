package quiz.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import quiz.components.molecules.Questions
import quiz.ui.theme.neutralColor
import quiz.ui.theme.primaryBackgroundColor

@Composable
fun HomeScreen() {
    val items = mapOf(
        "Quelle est la capitale de la France ?" to listOf("Marseille", "Lyon", "Paris", "Lille"),
        "Combien de départements, il y a-t-il en Ile-de-France ?" to listOf("5", "7", "2", "4"),
        "Quel est le nombre d'habitants en France ?" to listOf("48 580 148", "72 150 014", "82 485 485", "44 152 987")
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp),
        modifier = Modifier
            .fillMaxHeight()
            .background(neutralColor)
            .padding(20.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                textAlign = TextAlign.Center,
                color = primaryBackgroundColor,
                text = "Testez vos connaissances !",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Questions(items)
    }
}
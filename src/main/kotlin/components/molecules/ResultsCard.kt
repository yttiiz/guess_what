package quiz.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import quiz.ui.theme.primaryBackgroundColor

@Composable
fun ResultsCard(results: MutableList<String>) {
    // TODO test implementation of good answers
    val goodResponses = listOf("Paris", "7", "72 150 014")

    val givenResponses = results.filter { it != "no-response" }
    val givenResponsesCount = givenResponses.size
    val totalResponsesCount = results.size

    val score = givenResponses.foldIndexed(0) { index, acc, s ->
        if (s == goodResponses[index]) acc + 1 else acc
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Fin du questionnaire",
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "Vous avez répondu à ${
                if (givenResponsesCount == 0) "aucune" else "$givenResponsesCount"
            } question${
                if (givenResponsesCount > 1) "s" else ""
            } sur $totalResponsesCount."
        )
        Text(
            text = "Votre score final est de $score/$totalResponsesCount",
            style = MaterialTheme.typography.h3,
            color = primaryBackgroundColor
        )
    }

}
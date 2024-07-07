package quiz.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import quiz.components.atoms.RowCorrection
import quiz.components.atoms.RowResult
import quiz.data.mongo.Question

@Composable
fun ResultsCard(
    results: MutableList<String>,
    rawQuestions: List<Question>,
    userName: String
) {
    val givenResponses = results.filter { it != "no-response" }
    val givenResponsesCount = givenResponses.size
    val totalResponsesCount = results.size

    // Result (expected answers)
    val correction = rawQuestions.map { it.correction }
    val questionsTitle = rawQuestions.map { it.question.title }

    val basedScore = results.foldIndexed(0) { index, acc, s ->
        if (s == correction[index]) acc + 1 else acc
    }

    /**
     * Converts score based on rate 20.
     */
    val finalScore = { count: Int, score: Int ->
        val ratio = (20 / count).toFloat()
        val final = "%.2f".format(score.toFloat() * ratio)

        if (final.split(",")[1].contains("00")) {
            final.slice(0..final.length - 4)
        } else final
    }

    /**
     * Returns an __appreciation__ based on a given score.
     */
    val appreciation = { score: String ->
        val scoreInt = if (score.length == 1) score.toInt() else score.slice(0..1).toInt()

        when(scoreInt) {
            in 18..20 -> "Félicitations"
            16, 17 -> "Très bien"
            14, 15 -> "Bien"
            12, 13 -> "Assez bien"
            10, 11 -> "Passable"
            8, 9 -> "Moyen"
            6, 7 -> "Très moyen"
            else -> "A revoir"
        }
    }

    val score = finalScore(totalResponsesCount, basedScore)

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
        RowResult(
            userName,
            score,
            appreciation
        )
        RowCorrection(
            questionsTitle,
            correction,
            results
        )
    }
}
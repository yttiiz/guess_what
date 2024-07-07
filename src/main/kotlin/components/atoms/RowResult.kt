package quiz.components.atoms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import quiz.ui.theme.primaryBackgroundColor
import quiz.ui.theme.secondaryBackgroundColor

@Composable
fun RowResult(
    userName: String,
    score: String,
    appreciation: (String) -> String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                append("$userName, votre score est de ")
                withStyle(
                    style = SpanStyle(color = secondaryBackgroundColor)
                ) {
                    append("${score}/20")
                }
                append(" !")
            },
            style = MaterialTheme.typography.h3,
            color = primaryBackgroundColor
        )
        Text(text = buildAnnotatedString {
            append("Appréciation : ")
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold)
            ) {
                append(appreciation(score))
            }
            append(" !")
            toAnnotatedString()
        },
            color = primaryBackgroundColor
        )
    }
}
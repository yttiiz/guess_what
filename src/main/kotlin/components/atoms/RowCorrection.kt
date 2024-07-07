package quiz.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import quiz.ui.theme.*

@Composable
fun RowCorrection(
    questionsTitle: List<String>,
    correction: List<String>,
    result: MutableList<String>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.hsl(0F, 0F, 0.8F, 0.3F))
            .padding(40.dp)
    ) {
        Row {
            Text("Les bonnes réponses étaient :")
        }
        Row {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                for (i in correction.indices) {
                    val icon = { code: Int -> String(Character.toChars(code)) }
                    val isResponseCorrect = result[i] == correction[i]
                    val color = if (isResponseCorrect) successBackgroundColor else warningBackgroundColor
                    val sign = if (isResponseCorrect) icon(0x1F44C) else icon(0x1F44E)
                    val userResponse = if (result[i] == "no-response") "aucune réponse donnée" else result[i]

                    Text(text = buildAnnotatedString {
                        append("${i + 1} - ${questionsTitle[i]}\n")
                        withStyle(
                            style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = primaryBackgroundColor
                            )
                        ) {
                            append(correction[i])
                        }
                        append(" • votre réponse : ")
                        withStyle(style = SpanStyle(color = color)
                        ) {
                            append(userResponse)
                        }
                        append(" $sign")
                        toAnnotatedString()
                    })
                }
            }
        }
    }
}
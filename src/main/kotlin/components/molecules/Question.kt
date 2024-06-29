package quiz.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import quiz.ui.theme.primaryForegroundColor

@Composable
fun Questions(
    items: Map<String, List<String>>,
) {
    // Questions
    val questions by remember { mutableStateOf(items.iterator()) }
    val (currentQuestion, setCurrentQuestion) = remember { mutableStateOf(questions.next()) }
    var isLastQuestionReached by remember { mutableStateOf(false) }

    // Responses
    val (selectedOption, setSelectedOption) = remember { mutableStateOf("") }
    val results by remember { mutableStateOf<MutableList<String>>(mutableListOf()) }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(40.dp)
            .width(800.dp)
            .height(600.dp)
    ) {
        Column {
            if (isLastQuestionReached) {
                Text("Fin du questionnaire")
            } else {
                Text(currentQuestion.key)
                currentQuestion.value.forEach {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (it == selectedOption),
                            onClick = { setSelectedOption(it) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = primaryForegroundColor,
                                unselectedColor = primaryForegroundColor
                            )
                        )
                        Text(text = it)
                    }
                }
                Button(
                    onClick = {
                        results.add(selectedOption)
                        if (questions.hasNext()) {
                            setCurrentQuestion(questions.next())
                        } else {
                            isLastQuestionReached = true
                        }
                    },
                ) {
                    Text("Valider")
                }
            }
        }
    }
}
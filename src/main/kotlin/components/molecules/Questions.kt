package quiz.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import quiz.components.atoms.ButtonDismiss
import quiz.components.atoms.ButtonSubmit
import quiz.ui.theme.primaryForegroundColor
import quiz.ui.theme.warningBackgroundColor

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
    var isOptionIsNotSet by remember { mutableStateOf(false) }

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
                //============| Title |============//
                Text(
                    text = currentQuestion.key,
                )
                //============| Items |============//
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    currentQuestion.value.forEach {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = (it == selectedOption),
                                onClick = {
                                    setSelectedOption(it)
                                    if (isOptionIsNotSet) isOptionIsNotSet = false
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = primaryForegroundColor,
                                    unselectedColor = primaryForegroundColor
                                )
                            )
                            Text(text = it)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
                if (isOptionIsNotSet) {
                    Text(
                        text = "Vous devez choisir une option !",
                        color = warningBackgroundColor,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }
                //============| Buttons |============//
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    ButtonDismiss(
                        textContent = "Passer",
                        modifier = Modifier.width(200.dp),
                        onClick = {
                            //TODO implements logic here
                        }
                    )
                    ButtonSubmit(
                        textContent = "Question Suivante",
                        modifier = Modifier.width(300.dp),
                        onClick = {
                            if (selectedOption.isEmpty()) {
                                isOptionIsNotSet = true
                                return@ButtonSubmit
                            } else isOptionIsNotSet = false

                            results.add(selectedOption)

                            //Reset option for next question.
                            setSelectedOption("")

                            if (questions.hasNext()) {
                                setCurrentQuestion(questions.next())
                            } else {
                                isLastQuestionReached = true
                            }
                        }
                    )
                }
            }
        }
    }
}
package quiz.components.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import quiz.components.atoms.ButtonDismiss
import quiz.components.atoms.ButtonSubmit
import quiz.components.molecules.ResultsCard
import quiz.data.mongo.Question
import quiz.ui.theme.primaryForegroundColor
import quiz.ui.theme.warningBackgroundColor

typealias QuestionType = MutableMap.MutableEntry<String, List<String>>

@Composable
fun Questions(rawQuestions: List<Question>, userName: String) {
    // Questions
    val questions by remember { mutableStateOf<Iterator<QuestionType>>(rawQuestions
        .foldIndexed<Question, MutableMap<String, List<String>>>(mutableMapOf()) { index, acc, question ->
            val key = "${index + 1}/${rawQuestions.size} - ${question.question.title}"
            acc[key] = question.question.propositions
            acc
        }.iterator()) }
    val (currentQuestion, setCurrentQuestion) = remember { mutableStateOf(questions.next()) }
    val (isLastQuestionReached, setIsLastQuestionReached) = remember { mutableStateOf(false) }

    // Responses
    val (selectedOption, setSelectedOption) = remember { mutableStateOf("") }
    val results by remember { mutableStateOf<MutableList<String>>(mutableListOf()) }
    var isOptionIsNotSet by remember { mutableStateOf(false) }

    /**
     * 1. Reset remember `selectedOption` to an empty string.
     * 2. Check if it's last question to show __current question__ or __final result__.
     */
    val handleQuestions = {
        //Reset option for next question.
        setSelectedOption("")

        if (questions.hasNext()) {
            setCurrentQuestion(questions.next())
        } else {
            setIsLastQuestionReached(true)
        }
    }

    Column {
        if (isLastQuestionReached) {
            ResultsCard(results, rawQuestions, userName)
        } else {
            //============| Title |============//
            Text(
                text = currentQuestion.key,
                style = MaterialTheme.typography.h4
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
                    text = "Vous devez choisir une réponse !",
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
                        results.add("no-response")
                        handleQuestions()
                    }
                )
                ButtonSubmit(
                    textContent = "Question suivante",
                    modifier = Modifier.width(300.dp),
                    onClick = {
                        if (selectedOption.isEmpty()) {
                            isOptionIsNotSet = true
                            return@ButtonSubmit
                        } else isOptionIsNotSet = false

                        results.add(selectedOption)
                        handleQuestions()
                    }
                )
            }
        }
    }
}
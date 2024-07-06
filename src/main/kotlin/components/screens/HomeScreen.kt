package quiz.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.launch
import quiz.components.molecules.LoadingCard
import quiz.components.organisms.Questions
import quiz.services.MongoClientConnexion
import quiz.data.mongo.Question
import quiz.data.mongo.User
import quiz.ui.theme.neutralColor
import quiz.ui.theme.primaryBackgroundColor

@Composable
fun HomeScreen(user: List<User>) {
    var rawQuestions by remember { mutableStateOf<List<Question>?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val quizTitle = dotenv().get("QUIZ_TITLE")
    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        rawQuestions = MongoClientConnexion.getQuestions()
        isLoading = rawQuestions.isNullOrEmpty()
    }

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
                text = "Testez vos connaissances sur $quizTitle !",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(40.dp)
                .width(800.dp)
                .height(600.dp)
        ) {
            if (isLoading) {
                LoadingCard()
            } else {
                Questions(
                    rawQuestions = rawQuestions as List<Question>,
                    userName = user.first().firstname
                )
            }
        }
    }
}
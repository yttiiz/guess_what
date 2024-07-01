package quiz.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import quiz.components.molecules.LoadingCard
import quiz.components.organisms.Questions
import quiz.data.mongo.MongoClientConnexion
import quiz.data.mongo.Question
import quiz.ui.theme.neutralColor
import quiz.ui.theme.primaryBackgroundColor

@Composable
fun HomeScreen() {
    var rawQuestions by remember { mutableStateOf<List<Question>?>(null) }
    var isLoading by remember { mutableStateOf(true) }

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
                text = "Testez vos connaissances !",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (isLoading) {
            LoadingCard()
        } else {
            Questions(rawQuestions as List<Question>)
        }
    }
}
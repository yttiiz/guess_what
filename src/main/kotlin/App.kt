import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import quiz.components.organisms.Header
import quiz.components.screens.HomeScreen
import quiz.data.mongo.MongoClientConnexion
import quiz.ui.theme.QuizTypography

@Composable
@Preview
fun App(name: String) {
    // Init Mongo connexion.
    MongoClientConnexion.init()

    // Display screen
    MaterialTheme(typography = QuizTypography) {
        Column(
        ) {
            Header(name)
            HomeScreen()
        }
    }
}
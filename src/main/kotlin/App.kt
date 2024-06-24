import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import quiz.components.organisms.Header
import quiz.components.screens.HomeScreen
import quiz.data.mongo.MongoClientConnexion

@Composable
@Preview
fun App(name: String) {
    // Init Mongo connexion.
    MongoClientConnexion.init()

    // Display screen
    MaterialTheme {
        Column(
        ) {
            Header(name)
            HomeScreen()
        }
    }
}
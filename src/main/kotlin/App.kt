import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import quiz.components.organisms.Header
import quiz.components.screens.ConnexionScreen
import quiz.data.mongo.MongoClientConnexion
import quiz.data.mongo.User
import quiz.ui.theme.QuizTypography

@Composable
@Preview
fun App(name: String) {
    // Init Mongo connexion.
    MongoClientConnexion.init()

    val (user, setUser) = remember { mutableStateOf<List<User>?>(null) }
    var isUserConnected by remember { mutableStateOf(false) }

    // Display screen
    MaterialTheme(typography = QuizTypography) {
        Column {
            Header(
                logoName = name,
                isConnected = isUserConnected,
                user = user
            )
            if (isUserConnected) {
                Text("Connected")
            } else {
                ConnexionScreen(
                    connected = { isUserConnected = !isUserConnected },
                    user = user,
                    setUser = setUser
                )
            }
        }
    }
}
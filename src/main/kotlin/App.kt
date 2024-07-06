package quiz

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import quiz.components.organisms.Header
import quiz.components.screens.ConnexionScreen
import quiz.components.screens.HomeScreen
import quiz.services.MongoClientConnexion
import quiz.data.mongo.User
import quiz.ui.theme.QuizTypography

@Composable
@Preview
fun App(name: String) {
    // Init Mongo connexion.
    MongoClientConnexion.init()

    val (user, setUser) = remember { mutableStateOf<List<User>?>(null) }
    var isUserConnected by remember { mutableStateOf(false) }
    val handleUserConnexion = { isUserConnected = !isUserConnected }

    // Display screen
    MaterialTheme(typography = QuizTypography) {
        Column {
            Header(
                logoName = name,
                isConnected = isUserConnected,
                user = user,
                connected = handleUserConnexion
            )
            if (isUserConnected) {
                HomeScreen(user = user as List<User>)
            } else {
                ConnexionScreen(
                    connected = handleUserConnexion,
                    user = user,
                    setUser = setUser
                )
            }
        }
    }
}
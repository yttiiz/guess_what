package quiz

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import quiz.components.molecules.UserCard
import quiz.components.organisms.Header
import quiz.components.screens.ConnexionScreen
import quiz.components.screens.HomeScreen
import quiz.data.UserViewModel
import quiz.services.MongoClientConnexion
import quiz.ui.theme.QuizTypography

@Composable
@Preview
fun App(name: String) {
    // Init Mongo connexion.
    MongoClientConnexion.init()

    // Init User and its ViewModel
    val userModel = UserViewModel()
    val userState = userModel.user.collectAsState()
    val user by remember { userState }

    var isUserConnected by remember { mutableStateOf(false) }
    var isDialogOpen by remember { mutableStateOf(false) }

    val handleUserConnexion = { isUserConnected = !isUserConnected }
    val handleDialog = { isDialogOpen = !isDialogOpen }
    val closeDialog = { isDialogOpen = false }

    // Display screen
    MaterialTheme(typography = QuizTypography) {
        Column {
            Header(
                logoName = name,
                isConnected = isUserConnected,
                user = user,
                connected = handleUserConnexion,
                iconBtnClicked = handleDialog
            )
            if (isUserConnected) {
                HomeScreen(user)

                // Display user info
                if (isDialogOpen) {
                    Dialog(
                        properties = DialogProperties(
                            dismissOnClickOutside = true,
                            dismissOnBackPress = true
                        ),
                        onDismissRequest = closeDialog
                    ) {
                        UserCard(user.first(), closeDialog)
                    }
                }
            } else {
                ConnexionScreen(
                    connected = handleUserConnexion,
                    viewModel = userModel
                )
            }
        }
    }
}
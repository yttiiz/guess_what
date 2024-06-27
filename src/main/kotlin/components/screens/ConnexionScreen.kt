package quiz.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import quiz.components.molecules.LoginForm
import quiz.data.mongo.User
import quiz.ui.theme.neutralColor

@Composable
fun ConnexionScreen(connected: () -> Unit, user: List<User>?, setUser: (List<User>?) -> Unit) {
    Box(
        modifier = Modifier.background(neutralColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            LoginForm(
                connected = connected,
                user = user,
                setUser = setUser,
            )
        }
    }
}
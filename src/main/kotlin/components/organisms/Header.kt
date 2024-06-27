package quiz.components.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import quiz.components.atoms.Logo
import quiz.components.molecules.UserProfil
import quiz.data.mongo.User
import quiz.ui.theme.primaryBackgroundColor

/**
 * The main `header` of the application.
 */
@Composable
fun Header(logoName: String, isConnected: Boolean, user: List<User>?): Unit {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryBackgroundColor)
            .padding(20.dp),
    ) {
        Logo(
            name = logoName,
            width = 40.0,
            height = 40.0,
        )
        Box {
            if (isConnected) {
                UserProfil(user!!.first())
            }
        }
    }
}

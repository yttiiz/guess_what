package quiz.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.cdimascio.dotenv.dotenv
import quiz.components.atoms.ButtonDismiss
import quiz.data.mongo.User
import quiz.ui.theme.secondaryBackgroundColor
import quiz.utils.DateHandler
import quiz.utils.ImageHandler

@Composable
fun UserCard(user: User, closeDialog: () -> Unit) {
    val host = dotenv().get("DATA_HOST")

    Column(
        modifier = Modifier
            .width(350.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp),
            )
    ) {
        Header(user, host)
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .background(
                    color = secondaryBackgroundColor,
                    shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)
                )
                .padding(20.dp)
        ) {
            Body(user)
            Footer(closeDialog)
        }
    }
}

@Composable
fun Header(
    user: User,
    host: String
) {
    Row {
        Column(
            modifier = Modifier
                .border(
                    width = 1.0.dp,
                    shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
                    color = secondaryBackgroundColor
                )
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = ImageHandler.loadFromUrl("$host/${user.photo}"),
                contentDescription = "photo de ${user.firstname} ${user.lastname}",
                modifier = Modifier.clip(CircleShape)
            )
            Text("${user.firstname} ${user.lastname}")
            Text(DateHandler.getAge(user.birth))
        }
    }
}

@Composable
fun Body(user: User) {
    Row {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Date de naissance : ${DateHandler.getBirth(user.birth)}")
            Text("Email : ${user.email}")
            Text("Métier : ${user.job}")
        }
    }
}

@Composable
fun Footer(closeDialog: () -> Unit) {
    Row {
        ButtonDismiss(textContent = "fermer", onClick = { closeDialog() })
    }
}

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
import androidx.compose.ui.unit.dp
import io.github.cdimascio.dotenv.dotenv
import quiz.data.mongo.User
import quiz.ui.theme.secondaryBackgroundColor
import quiz.utils.DateHandler
import quiz.utils.ImageHandler

@Composable
fun UserCard(user: User) {
    val host = dotenv().get("DATA_HOST")
    val spacing = Arrangement.spacedBy(10.dp)

    Column {
        Header(user, spacing, host)
        Body(user)
    }
}

@Composable
fun Header(
    user: User,
    spacing: Arrangement.HorizontalOrVertical,
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
            verticalArrangement = spacing,
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
            modifier = Modifier
                .background(
                    color = secondaryBackgroundColor,
                    shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)
                )
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Text("Date de naissance : ${DateHandler.getBirth(user.birth)}")
            Text("Email : ${user.email}")
            Text("Métier : ${user.job}")
        }
    }
}

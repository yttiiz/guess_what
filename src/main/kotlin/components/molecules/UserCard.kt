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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.cdimascio.dotenv.dotenv
import quiz.data.mongo.User
import quiz.ui.theme.secondaryBackgroundColor
import quiz.utils.DateHandler

@Composable
fun UserCard(user: User) {
    val spacing = Arrangement.spacedBy(10.dp)

    Column {
        Header(user, spacing)
        Body(user)
    }
}

@Composable
fun Header(user: User, spacing: Arrangement.HorizontalOrVertical) {
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
            val host = dotenv().get("DATA_HOST")
            val url = "$host/${user.photo}"

            Image(
                painter = painterResource("/drawable/image.jpeg"),
                contentDescription = "image of ${user.firstname} ${user.lastname}",
                modifier = Modifier
                    .width(150.dp)
                    .clip(CircleShape)
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
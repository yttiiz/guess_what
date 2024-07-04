package quiz.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import io.github.cdimascio.dotenv.dotenv
import quiz.data.mongo.User
import quiz.ui.theme.*
import quiz.utils.ImageHandler
import java.time.LocalTime

@Composable
fun UserProfil(user: User, connected: () -> Unit) {
    val host = dotenv().get("DATA_HOST")
    val greet = if (LocalTime.now().hour in 18 downTo 5) "Bonjour" else "Bonsoir"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        IconButton(
            onClick = { connected() },
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            ) {
            Icon(
                painter = painterResource(
                    "/drawable/icons/onBtn.svg"
                ),
                contentDescription = "close button",
                tint = neutralColor,
            )
        }
        Text(
            text = buildAnnotatedString {
                append("$greet ")
                withStyle(
                    style = SpanStyle(fontFamily = FontFamily(Font(montserratBold)))
                ) {
                    append(user.firstname)
                }
            },
            color = primaryForegroundColor
        )
        Image(
            bitmap = ImageHandler.loadFromUrl("$host/${user.photo}"),
            contentDescription = "photo de ${user.firstname} ${user.lastname}",
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .clip(CircleShape)
        )
    }
}
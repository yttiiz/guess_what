package quiz.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import quiz.data.mongo.User
import quiz.ui.theme.secondaryBackgroundColor
import quiz.utils.DateHandler
import java.io.File
import java.io.IOException

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

            AsyncImage(
                load = { loadImageBitmap(File(url).inputStream()) },
                painterFor = { remember { BitmapPainter(it) } },
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

@Composable
fun <T> AsyncImage(
    load: suspend () -> T,
    painterFor: @Composable (T) -> Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    val image: T? by produceState<T?>(null) {
        value = withContext(Dispatchers.IO) {
            try {
                load()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }

    if (image != null) {
        Image(
            painter = painterFor(image!!),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}
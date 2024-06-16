package quiz.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.request.ImageRequest
import quiz.data.remote.User
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

        LaunchedEffect(user) {
            val imageLoader = ImageLoader(PlatformContext.INSTANCE)
            val request = ImageRequest.Builder(context = PlatformContext.INSTANCE)
                .data(user.image)
                .build()
            val image = imageLoader.execute(request).image

            println(image)
        }
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
                painter = painterResource("/drawable/image.jpeg"),
                contentDescription = "image of ${user.firstName} ${user.lastName}",
                modifier = Modifier
                    .width(150.dp)
                    .clip(CircleShape)
            )
            Text("${user.firstName} ${user.lastName}")
            Text(DateHandler.getAge(user.birthDate))
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
            Text("Date de naissance : ${user.birthDate}")
            Text("Email : ${user.email}")
            Text("Numéro de téléphone : ${user.phone}")
        }
    }
}
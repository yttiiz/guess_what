package quiz.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import quiz.data.remote.User
import quiz.utils.DateHandler

@Composable
fun UserCard(user: User) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
//        Image(
//            contentDescription = "${user.username} photo",
//            painter = painterResource(),
//            modifier = Modifier
//                .width(30.dp)
//                .height(30.dp)
//        )
        Text("${user.firstName} ${user.lastName}")
        Text(DateHandler.getAge(user.birthDate))
    }
}
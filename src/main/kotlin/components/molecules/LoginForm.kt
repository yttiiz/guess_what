import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import quiz.components.molecules.UserCard
import quiz.data.mongo.MongoClientConnexion
import quiz.data.mongo.User

@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val (user, setUser) = remember { mutableStateOf<List<User>?>(null) }

    val getUsersOnClick: () -> Unit = {
        coroutineScope.launch {
            setUser(MongoClientConnexion.getUser(email))
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier.width(300.dp)
    ) {
        TextField(
            label = { Text("Email") },
            placeholder = { Text("Entrez votre email...") },
            onValueChange = { email = it },
            value = email,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            label = { Text("Mot de passe") },
            placeholder = { Text("Entrez votre mot de passe...") },
            onValueChange = { password = it },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        ButtonSubmit(
            text = "Soumettre",
            fetchData = getUsersOnClick
        )

        if (!user.isNullOrEmpty()) {
            UserCard(user.first())
        }
    }
}
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import quiz.components.molecules.UserCard
import quiz.data.remote.Users
import quiz.data.remote.api.KtorApiClient

@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val (users, setUsers) = remember { mutableStateOf<Users?>(null) }

    val getUsersOnClick: () -> Unit = {
        coroutineScope.launch {
            val api = KtorApiClient()
            setUsers(api.getUsers(3))
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
            modifier = Modifier.fillMaxWidth()
        )
        ButtonSubmit(
            text = "Soumettre",
            fetchData = getUsersOnClick
        )

        if (users != null) {
            for (user in users.users) {
                UserCard(user)
            }
        }
    }
}
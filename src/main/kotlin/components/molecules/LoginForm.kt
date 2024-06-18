import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import quiz.components.atoms.TextFieldForm
import quiz.components.molecules.UserCard
import quiz.data.mongo.MongoClientConnexion
import quiz.data.mongo.User

@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var emailMessage by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val (user, setUser) = remember { mutableStateOf<List<User>?>(null) }

    val getUsersOnClick: () -> Unit = {
        val isFormValidationOk = email.isEmpty() || password.isEmpty()
        val getMessage: (str: String) -> String = { str -> "Vous devez renseigner $str." }

        if (isFormValidationOk) {
            if (email.isEmpty()) emailMessage = getMessage("une adresse email")
            if (password.isEmpty()) passwordMessage = getMessage("un mot de passe")

        } else coroutineScope.launch {
            if (emailMessage.isNotEmpty()) emailMessage = ""
            if (passwordMessage.isNotEmpty()) passwordMessage = ""

            setUser(MongoClientConnexion.getUser(email))
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.width(300.dp)
    ) {
        TextFieldForm(
            label = "Email",
            placeholder = "Entrez votre email...",
            value = email,
            message = emailMessage,
            onChange = { email = it }
        )
        TextFieldForm(
            label = "Mot de passe",
            placeholder = "Entrez votre mot de passe...",
            value = password,
            message = passwordMessage,
            onChange = { password = it },
            isPasswordField = true
        )
        ButtonSubmit(
            textContent = "Soumettre",
            fetchData = getUsersOnClick
        )

        if (user.isNullOrEmpty()) {
            Row {
                Text(
                    modifier = Modifier.padding(10.dp),
                    color = Color.Red,
                    text = (if (user != null) "Aucun utilisateur trouvé avec l'adresse : $email" else "")
                )
            }
        } else UserCard(user.first())
    }
}
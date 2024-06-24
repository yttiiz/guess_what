package quiz.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import quiz.components.atoms.*
import quiz.data.mongo.MongoClientConnexion
import quiz.data.mongo.User
import quiz.ui.theme.warningBackgroundColor

@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var emailMessage by remember { mutableStateOf("") }
    var emailSnapshot by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordMessage by remember { mutableStateOf("") }
    var isPasswordIncorrect by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val (user, setUser) = remember { mutableStateOf<List<User>?>(null) }

    val handleUserVerification: () -> Unit = {
        val isFormValidationNotOk = email.isEmpty() || password.isEmpty()
        val getMessage: (str: String) -> String = { str -> "Vous devez renseigner $str." }

        if (isFormValidationNotOk) {
            if (email.isEmpty()) emailMessage = getMessage("une adresse email")
            if (password.isEmpty()) passwordMessage = getMessage("un mot de passe")

        } else coroutineScope.launch {
            if (emailMessage.isNotEmpty()) emailMessage = ""
            if (passwordMessage.isNotEmpty()) passwordMessage = ""

            val result = MongoClientConnexion.verifyUser(email, password)

            setUser(result.first)
            isPasswordIncorrect = result.second.contains("wrong password")
        }

        emailSnapshot = email
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
            textContent = "Connexion",
            onClick = handleUserVerification
        )

        if (user.isNullOrEmpty()) {
            Row {
                Text(
                    modifier = Modifier.padding(10.dp),
                    color = warningBackgroundColor,
                    text = (
                        if (user != null) {
                            if (isPasswordIncorrect) "Votre mot de passe est incorrect"
                            else "Aucun utilisateur trouvé avec l'adresse : $emailSnapshot"
                        } else "")
                )
            }
        } else UserCard(user.first())
    }
}
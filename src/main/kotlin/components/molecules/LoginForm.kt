package quiz.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import quiz.components.atoms.ButtonSubmit
import quiz.components.atoms.TextFieldForm
import quiz.data.mongo.MongoClientConnexion
import quiz.data.mongo.User
import quiz.ui.theme.neutralColor
import quiz.ui.theme.warningBackgroundColor

@Composable
fun LoginForm(connected: () -> Unit, user: List<User>?, setUser: (List<User>?) -> Unit) {
    var email by remember { mutableStateOf("") }
    var emailMessage by remember { mutableStateOf("") }
    var emailSnapshot by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordMessage by remember { mutableStateOf("") }
    var isPasswordIncorrect by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

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

            if (!user.isNullOrEmpty() && !isPasswordIncorrect) connected()
        }

        emailSnapshot = email
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(40.dp)
            .width(300.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource("/drawable/icons/user.svg"),
                contentDescription = "user icon",
                tint = neutralColor,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
        }
        Text(
            textAlign = TextAlign.Center,
            color = neutralColor,
            text = "Connectez-vous !",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.fillMaxWidth()
        )
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
        }
    }
}
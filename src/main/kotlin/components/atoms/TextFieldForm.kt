package quiz.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text2.TextFieldDecorator
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.cdimascio.dotenv.dotenv
import quiz.ui.theme.montserratRegular
import quiz.ui.theme.neutralColor
import quiz.ui.theme.primaryBackgroundColor
import quiz.ui.theme.warningBackgroundColor

@Composable
fun TextFieldForm(
    label: String,
    placeholder: String,
    value: String,
    message: String,
    onChange: (String) -> Unit,
    isPasswordField: Boolean = false,
) {
    val host = dotenv().get("DATA_HOST")
    val uriHandler = LocalUriHandler.current

    Row {
        Column {
            if (isPasswordField) {
                var isEyeOpen by remember { mutableStateOf(true) }

                TextField(
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    onValueChange = onChange,
                    value = value,
                    visualTransformation = if (isEyeOpen) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(onClick = { isEyeOpen = !isEyeOpen }) {
                            Icon(
                                painter = painterResource(
                                    "/drawable/icons/eye-${if (isEyeOpen) "close" else "open"}.svg"
                                ),
                                contentDescription = "eye password",
                                tint = neutralColor,
                            )
                        }
                    },
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = neutralColor,
                        focusedBorderColor = primaryBackgroundColor,
                        focusedLabelColor = primaryBackgroundColor
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                ClickableText(
                    text = AnnotatedString(
                        text = "mot de passe oublié ?",
                        spanStyle = SpanStyle(
                            fontFamily = FontFamily(Font(montserratRegular)),
                            textDecoration = TextDecoration.Underline
                        )
                    ),
                    onClick = { uriHandler.openUri("$host/login") },
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                TextField(
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    onValueChange = onChange,
                    value = value,
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = neutralColor,
                        focusedBorderColor = primaryBackgroundColor,
                        focusedLabelColor = primaryBackgroundColor
                    )
                )
            }
            if (value.isEmpty() && message.isNotEmpty()) {
                Spacer(modifier = Modifier.height(5.dp))
            }
            Text(
                text = if (value.isEmpty()) message else "",
                color = warningBackgroundColor,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
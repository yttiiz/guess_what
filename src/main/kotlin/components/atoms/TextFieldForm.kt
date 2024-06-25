package quiz.components.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
import quiz.ui.theme.montserratRegular
import quiz.ui.theme.neutralColor
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
                    onClick = {
                        //TODO implement logic here later
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                TextField(
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    onValueChange = onChange,
                    value = value,
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth()
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
package quiz.components.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
                TextField(
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    onValueChange = onChange,
                    value = value,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                TextField(
                    label = { Text(label) },
                    placeholder = { Text(placeholder) },
                    onValueChange = onChange,
                    value = value,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (value.isEmpty() && message.isNotEmpty()) {
                Spacer(modifier = Modifier.height(5.dp))
            }
            Text(
                text = if (value.isEmpty()) message else "",
                color = warningBackgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
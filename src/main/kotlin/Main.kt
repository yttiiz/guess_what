import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

const val APP_NAME = "Quiz Game"
fun main() = application {
    Window(
        resizable = true,
        title = APP_NAME,
        onCloseRequest = ::exitApplication
    ) {
        App(APP_NAME)
    }
}

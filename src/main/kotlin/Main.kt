import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

const val APP_NAME = "Quiz Game"
fun main() = application {
    Window(
        icon = painterResource("/drawable/icons/yz_logo.svg"),
        resizable = true,
        title = APP_NAME,
        onCloseRequest = ::exitApplication
    ) {
        window.minimumSize = Dimension(1920, 1080)

        App(APP_NAME)
    }
}

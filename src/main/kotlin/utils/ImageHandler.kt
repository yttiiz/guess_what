package quiz.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.net.URL

object ImageHandler {

    @Composable
    fun loadFromUrl(url: String) = Image
        .makeFromEncoded(URL(url).readBytes())
        .toComposeImageBitmap()
}
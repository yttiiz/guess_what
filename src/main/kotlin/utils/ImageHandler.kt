package quiz.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.net.URI

object ImageHandler {

    @Composable
    fun loadFromUrl(url: String) = Image
        .makeFromEncoded(URI(url).toURL().readBytes())
        .toComposeImageBitmap()
}
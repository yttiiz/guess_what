package quiz.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.*

val QuizTypography = Typography(
    defaultFontFamily = FontFamily(Font(montserratRegular)),
    h1 = TextStyle(
        fontSize = 42.sp,
        lineHeight = 56.sp,
        fontFamily = FontFamily(Font(montserratBold))
    ),
    h2 = TextStyle(
        fontSize = 32.sp,
        lineHeight = 44.sp,
        fontFamily = FontFamily(Font(montserratBold))
    ),
    h3 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 30.sp,
        fontFamily = FontFamily(Font(montserratBold))
    ),
    h4 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        fontFamily = FontFamily(Font(montserratBold))
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
    )
)
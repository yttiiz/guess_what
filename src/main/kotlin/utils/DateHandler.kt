package quiz.utils

import java.text.DateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class DateHandler {
    companion object {
        private fun convertStr(it: String): CharSequence {
            return if (it.length < 2) "0$it" else it
        }

        private fun formatDateCorrectly(birth: Date): String {
            return birth
                .toInstant()
                .toString()
                .split("T")[0]
                .split("-")
                .joinToString(
                    separator = "-",
                    transform = { convertStr(it) }
                )
        }

        private fun formatStringDateCorrectly(birth: String): String {
            return birth
                .split("-")
                .joinToString(
                    separator = "-",
                    transform = { convertStr(it) }
                )
        }

        fun getAge(birth: Any): String {
            return when(birth) {
                is Date -> "${Period.between(
                    LocalDate.parse(formatDateCorrectly(birth)),
                    LocalDate.now(),
                ).years} ans"

                is String -> "${Period.between(
                    LocalDate.parse(formatStringDateCorrectly(birth)),
                    LocalDate.now(),
                ).years} ans"

                is Number -> "$birth ans"

                else -> "not a birth"
            }
        }

        fun getBirth(
            birth: Date,
            locale: Locale = Locale.FRANCE
        ): String {
            return LocalDate
                .parse(formatDateCorrectly(birth))
                .format(
                    DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.LONG)
                        .withLocale(locale)
                )
        }
    }
}
package quiz.utils

import java.time.LocalDate
import java.time.Period

class DateHandler {
    companion object {
        private fun formatDateCorrectly(birth: String): String {
            return birth.split("-").joinToString("-") {
                if (it.length < 2) "0$it" else it
            }
        }

        fun getAge(birth: String): String {
            return "${Period.between(
                LocalDate.parse(formatDateCorrectly(birth)),
                LocalDate.now(),
            ).years} ans"
        }
    }
}
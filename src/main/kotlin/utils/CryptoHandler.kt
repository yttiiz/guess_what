package quiz.utils

import com.toxicbakery.bcrypt.Bcrypt

object CryptoHandler {
    fun isPasswordOk(hash: String, password: String) = Bcrypt.verify(
            input = password,
            expected = hash.toByteArray()
        )
}
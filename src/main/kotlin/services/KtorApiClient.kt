package quiz.services

import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import quiz.data.remote.Users

class KtorApiClient {
    companion object {
        private val httpClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }

        suspend fun getUsers(limit: Byte? = null): Users {
            val url = URLBuilder().apply {
                takeFrom("https://dummyjson.com/users${if (limit != null) "?limit=$limit" else ""}")
            }

            return httpClient.get(url.build()).body()
        }
    }
}
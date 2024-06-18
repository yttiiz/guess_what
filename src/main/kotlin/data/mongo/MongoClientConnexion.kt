package quiz.data.mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.bson.Document

object MongoClientConnexion {
    private lateinit var client: MongoClient
    private lateinit var database: MongoDatabase
    private var username: String
    private var password: String

    init {
        val dotenv = dotenv()
        username = dotenv["MONGODB_USERNAME"]
        password = dotenv["MONGODB_PASSWORD"]
    }

    fun main() {
        val connection = "mongodb+srv://$username:$password@atlascluster.mmc5tn8.mongodb.net/?retryWrites=true&w=majority&appName=AtlasCluster"

        val serverApi = ServerApi
            .builder()
            .version(ServerApiVersion.V1)
            .build()

        val mongoClientSettings = MongoClientSettings
            .builder()
            .applyConnectionString(ConnectionString(connection))
            .serverApi(serverApi)
            .build()

        client = MongoClient.create(mongoClientSettings)
        database = client.getDatabase("main")
    }

    suspend fun getUser(email: String): List<User> {
        return database
            .getCollection<User>("users")
            .find(Document("email", email))
            .toList()
    }
}
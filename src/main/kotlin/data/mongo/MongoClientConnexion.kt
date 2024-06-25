package quiz.data.mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.flow.toList
import org.bson.Document
import quiz.utils.CryptoHandler

object MongoClientConnexion {
    private lateinit var client: MongoClient
    private lateinit var database: MongoDatabase
    private var username: String
    private var password: String
    private var host: String

    init {
        val dotenv = dotenv()
        username = dotenv["MONGODB_USERNAME"]
        password = dotenv["MONGODB_PASSWORD"]
        host = dotenv["MONGODB_HOST"]
    }

    fun init() {
        val connection = "mongodb+srv://$username:$password@$host/?retryWrites=true&w=majority&appName=AtlasCluster"

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

    suspend fun verifyUser(email: String, password: String): Pair<List<User>, String> {
        val user = database
            .getCollection<User>("users")
            .find(Document("email", email))
            .toList()

        return if (user.isNotEmpty()) {
            if (CryptoHandler.isPasswordOk(user[0].hash, password)) {
                user to "password ok"
            } else emptyList<User>() to "wrong password"
        } else emptyList<User>() to "no user found"
    }
}
package quiz.data.mongo

import org.bson.types.ObjectId
import java.util.Date

data class User(
    val _id: ObjectId,
    val firstname: String,
    val lastname: String,
    val email: String,
    val birth: Date,
    val role: String,
    val job: String,
    val hash: String,
    val photo: String
)

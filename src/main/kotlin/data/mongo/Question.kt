package quiz.data.mongo

import org.bson.types.ObjectId

data class Question(
    val _id: ObjectId,
    val question: QuestionItems,
    val correction: String
)

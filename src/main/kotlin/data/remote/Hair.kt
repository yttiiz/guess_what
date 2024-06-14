package quiz.data.remote


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Hair(
    @SerializedName("color")
    val color: String,
    @SerializedName("type")
    val type: String
)
package quiz.data.remote


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)
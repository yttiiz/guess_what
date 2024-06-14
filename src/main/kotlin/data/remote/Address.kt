package quiz.data.remote


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("country")
    val country: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("stateCode")
    val stateCode: String
)
package quiz.data.remote


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
    @SerializedName("coin")
    val coin: String,
    @SerializedName("network")
    val network: String,
    @SerializedName("wallet")
    val wallet: String
)
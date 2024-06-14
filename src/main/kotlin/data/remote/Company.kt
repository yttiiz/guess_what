package quiz.data.remote


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerializedName("address")
    val address: Address,
    @SerializedName("department")
    val department: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String
)
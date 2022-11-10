package network.response

import com.google.gson.annotations.SerializedName
import data.vos.SnackVO

data class SnackResponse(
    @SerializedName("data")
    val data:List<SnackVO>
)

package network.response

import com.google.gson.annotations.SerializedName
import data.vos.CardVO

data class CardResponse(
    @SerializedName("data")
    val data:List<CardVO>
)

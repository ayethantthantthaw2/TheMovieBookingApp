package network.response

import com.google.gson.annotations.SerializedName
import data.vos.CheckOutVO

data class CheckoutResponse(
    @SerializedName("data")
    val data:CheckOutVO

)

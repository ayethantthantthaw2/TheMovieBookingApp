package network.response

import com.google.gson.annotations.SerializedName
import data.vos.PaymentMethodVO

data class PaymentMethodResponse(
    @SerializedName("data")
    val data:List<PaymentMethodVO>
)

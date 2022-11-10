package network.response

import com.google.gson.annotations.SerializedName
import data.vos.RegisterVO
import data.vos.UserVO

data class RegisterResponse (
    @SerializedName("data")
    val data:UserVO,

    @SerializedName("token")
    val token:String


)


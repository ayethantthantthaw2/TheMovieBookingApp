package data.vos

import com.google.gson.annotations.SerializedName

data class RegisterVO(
    @SerializedName("code")
    val code:Int,

    @SerializedName("message")
    val message:String,

    @SerializedName("data")
    val data:UserVO,

    @SerializedName("token")
    val token:String,


)

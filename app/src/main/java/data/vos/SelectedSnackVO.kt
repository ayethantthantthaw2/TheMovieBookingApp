package data.vos

import com.google.gson.annotations.SerializedName

data class SelectedSnackVO(
    @SerializedName("id")
    var id:Int,

    @SerializedName("quantity")
    var quantity:Int
)

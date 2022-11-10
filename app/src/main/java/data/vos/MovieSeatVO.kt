package data.vos

import com.google.gson.annotations.SerializedName
import utils.SEAT_TYPE_AVAILABLE
import utils.SEAT_TYPE_TAKEN
import utils.SEAT_TYPE_TEXT

data class MovieSeatVO(
    var isSelected:Boolean=false,

    @SerializedName("id")
    val id:Int,

    @SerializedName("seat_name")
    val seatName:String,

    @SerializedName("symbol")
    val symbol:String?,

    @SerializedName("price")
    val price:Int,

    @SerializedName("type")
     val type:String?,
) {
    fun isMovieSeatAvailable():Boolean{
        return type== SEAT_TYPE_AVAILABLE
    }
    fun isMovieSeatTaken():Boolean{
        return type== SEAT_TYPE_TAKEN
    }
    fun isMovieSeatRowTitle():Boolean{
        return type== SEAT_TYPE_TEXT
    }


}

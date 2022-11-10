package data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cards")
data class CardVO(
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "card_holder")
    @SerializedName("card_holder")
    val cardHolder:String?,

    @ColumnInfo(name = "card_number")
    @SerializedName("card_number")
    val cardNumber:String?,

    @ColumnInfo(name = "expiration_date")
    @SerializedName("expiration_date")
    val expirationDate:String?,

    @ColumnInfo(name = "card_type")
    @SerializedName("card_type")
    val cardType:String?,

    @ColumnInfo(name = "isSelected")
    var isSelected:Boolean=false
)

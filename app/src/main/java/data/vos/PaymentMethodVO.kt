package data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "paymentMethods")
data class PaymentMethodVO(

    @ColumnInfo(name="id")
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name:String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description:String,

    @ColumnInfo(name = "isSelected")
    var isSelected:Boolean=false

    )

package persistence.TypeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.vos.CardVO

class CardTypeConverter {
    @TypeConverter
    fun toString(cards:List<CardVO>):String{
        return Gson().toJson(cards)
    }
    @TypeConverter
    fun toCollectionVO(cardListGsonStr: String): List<CardVO>{
        val cardsVOType=object: TypeToken<List<CardVO>>(){}.type
        return Gson().fromJson(cardListGsonStr,cardsVOType)
    }
}
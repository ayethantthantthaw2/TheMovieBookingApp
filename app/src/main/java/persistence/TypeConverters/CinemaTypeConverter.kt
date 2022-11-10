package persistence.TypeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.vos.CinemaVO
import data.vos.CollectionVO

class CinemaTypeConverter {
    @TypeConverter
    fun toString(cinema: List<CinemaVO>?): String {
        return Gson().toJson(cinema)
    }

    @TypeConverter
    fun toCinemaVO(cinemaListGsonStr: String): List<CinemaVO>? {
        val cinemaVOType = object : TypeToken<List<CinemaVO>?>() {}.type
        return Gson().fromJson(cinemaListGsonStr, cinemaVOType)
    }
}
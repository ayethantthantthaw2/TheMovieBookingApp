package persistence.TypeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.vos.CollectionVO

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collection: CollectionVO?): String {
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(collectionListGsonStr: String): CollectionVO? {
        val collectionVOType = object : TypeToken<CollectionVO?>() {}.type
        return Gson().fromJson(collectionListGsonStr, collectionVOType)
    }
}
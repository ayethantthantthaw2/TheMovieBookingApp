package persistence.TypeConverters

import androidx.room.TypeConverter
import data.vos.ProductionCompanyVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompaniesTypeConverter {
    @TypeConverter
    fun toString(productionCompaniesList:List<ProductionCompanyVO>?):String{
        return Gson().toJson(productionCompaniesList)
    }

    @TypeConverter
    fun toProductionCompaniesList(productionCompaniesListJsonString:String):List<ProductionCompanyVO>?{
        val productionCompaniesListType=object : TypeToken<List<ProductionCompanyVO>?>(){}.type
        return Gson().fromJson(productionCompaniesListJsonString,productionCompaniesListType)
    }
}
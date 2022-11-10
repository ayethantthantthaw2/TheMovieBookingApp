package data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import persistence.TypeConverters.CinemaTypeConverter

@Entity(tableName = "dateCinemaAndTimeslots")
@TypeConverters(
    CinemaTypeConverter::class
)
data class DateCinemaAndTimeslots(

    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String="",

    @ColumnInfo(name = "cinemas")
    var cinemas:List<CinemaVO>
)
package persistence.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.vos.DateCinemaAndTimeslots
import data.vos.UserVO

@Dao
interface TimeslotsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinema(cinema: DateCinemaAndTimeslots)

    @Query("SELECT * FROM dateCinemaAndTimeslots WHERE date=:date")
    fun getCinema(date:String?): DateCinemaAndTimeslots

    @Query("DELETE FROM dateCinemaAndTimeslots")
    fun deleteCinema()
}
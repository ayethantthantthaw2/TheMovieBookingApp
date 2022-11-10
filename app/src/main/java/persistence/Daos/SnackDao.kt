package persistence.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.vos.DateCinemaAndTimeslots
import data.vos.SnackVO

@Dao
interface SnackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSnacks(snacks:List<SnackVO>?)

    @Query("SELECT * FROM snacks")
    fun getSnack(): List<SnackVO>

    @Query("DELETE FROM snacks")
    fun deleteSnacks()
}
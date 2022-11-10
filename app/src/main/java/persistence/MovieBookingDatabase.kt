package persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.vos.*
import persistence.Daos.*

@Database(
    entities = [UserVO::class, MovieVO::class, DateCinemaAndTimeslots::class, SnackVO::class, PaymentMethodVO::class,CardVO::class],
    version = 27,
    exportSchema = false
)
abstract class MovieBookingDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "THE_USER_DB"

        var dbInstance: MovieBookingDatabase? = null

        fun getDBInstance(context: Context): MovieBookingDatabase? {

            when (dbInstance) {
                null -> {
                    dbInstance =
                        Room.databaseBuilder(context, MovieBookingDatabase::class.java, DB_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()

                }
            }
            return dbInstance

        }
    }

    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
    abstract fun timeslotsDao(): TimeslotsDao
    abstract fun snackDao(): SnackDao
    abstract fun paymentMethodDao():PaymentMethodDao
    abstract fun cardsDao():CardDao


}
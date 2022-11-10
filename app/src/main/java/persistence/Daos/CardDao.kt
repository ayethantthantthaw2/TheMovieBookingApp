package persistence.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.vos.CardVO
import data.vos.PaymentMethodVO

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCards(cards:List<CardVO>?)

    @Query("SELECT * FROM cards")
    fun getCards(): List<CardVO>

    @Query("DELETE FROM cards")
    fun deleteCard()

}
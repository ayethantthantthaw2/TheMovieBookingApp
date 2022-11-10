package persistence.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.vos.PaymentMethodVO
import data.vos.SnackVO

@Dao
interface PaymentMethodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethod(snacks:List<PaymentMethodVO>?)

    @Query("SELECT * FROM paymentMethods")
    fun getPaymentMethod(): List<PaymentMethodVO>

    @Query("DELETE FROM paymentMethods")
    fun deletePaymentMethod()
}
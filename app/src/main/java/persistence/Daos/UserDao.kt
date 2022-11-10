package persistence.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.vos.CardVO
import data.vos.UserVO

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserVO)

    @Query("INSERT INTO user (token) VALUES (:token)")
    fun insertToken(token: String)

    @Query("SELECT * FROM user")
    fun getUser(): List<UserVO>

    @Query("DELETE FROM user")
    fun deleteUser()


}
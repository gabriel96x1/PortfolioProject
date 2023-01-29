package com.rzs.corroutinesproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rzs.corroutinesproject.domain.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>?>
}
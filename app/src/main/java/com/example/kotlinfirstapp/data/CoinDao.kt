package com.example.kotlinfirstapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinfirstapp.model.User
import io.reactivex.Single

@Dao
interface CoinDao {

    @Query("SELECT*FROM user_table")
    fun getUser(): Single<User>

    @Insert
    fun insertUser(user: User)
}
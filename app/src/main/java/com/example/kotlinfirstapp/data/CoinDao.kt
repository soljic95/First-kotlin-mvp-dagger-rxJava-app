package com.example.kotlinfirstapp.data

import androidx.room.*
import com.example.kotlinfirstapp.model.User
import io.reactivex.Single

@Dao
interface CoinDao {

    @Query("SELECT*FROM user_table WHERE username = :userName")
    fun getUser(userName: String): Single<User>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: User)

    @Update
    fun logUser(user: User)

    @Query("SELECT * FROM user_table WHERE isUserLoggedIn = 1")
    fun getLoggedUser(): Single<User>


}
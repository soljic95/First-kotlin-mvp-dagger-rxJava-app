package com.example.kotlinfirstapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "username")
    val userName: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "isUserLoggedIn")
    var isUserLoggedIn: Boolean = false,
    @ColumnInfo(name = "userCoins")
    var userCoins: ArrayList<String> = ArrayList()
)





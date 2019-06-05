package com.example.kotlinfirstapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinfirstapp.model.User

@Database(entities = [User::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    var instance: CoinDatabase? = null

    companion object {
        @Volatile
        private var INSTANCE: CoinDatabase? = null

        fun getDatabase(context: Context): CoinDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinDatabase::class.java,
                    "coin_databse"
                )
                    .build()
                INSTANCE = instance
                return instance


            }
        }
    }


    /*@Synchronized
    fun getInstance(context: Context): CoinDatabase {

        if (instance == null) {
            instance = Room.databaseBuilder<CoinDatabase>(
                context.applicationContext,
                CoinDatabase::class.java, context.getString(R.string.coinDatabase)
            )
                .fallbackToDestructiveMigration()
                .build()
        }
        return instance as CoinDatabase
    }*/


}
package com.example.kotlinfirstapp.data

import android.content.Context
import androidx.room.*
import com.example.kotlinfirstapp.converter.Converters
import com.example.kotlinfirstapp.model.Data
import com.example.kotlinfirstapp.model.User

@Database(entities = [User::class], version = 5)
@TypeConverters(Converters::class)
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
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance


            }
        }
    }


}
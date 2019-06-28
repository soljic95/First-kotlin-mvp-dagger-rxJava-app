package com.example.kotlinfirstapp.dagger.module

import android.content.Context
import com.example.kotlinfirstapp.dagger.qualifiers.ForApplication
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.data.CoinDatabase
import com.example.kotlinfirstapp.dagger.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val context: Context) {


    @Provides
    @ApplicationScope
    @ForApplication
    fun provideContext(): Context {
        return context
    }

    @Provides
    @ApplicationScope
    fun provideCoinDatabase(@ForApplication context: Context): CoinDatabase {
        return CoinDatabase.getDatabase(context)
    }

    @Provides
    @ApplicationScope
    fun provideCoinDao(coinDatabase: CoinDatabase): CoinDao {
        return coinDatabase.coinDao()
    }

    interface Exposes {
        fun getCoinDatabase(): CoinDatabase
        fun getCoinDao(): CoinDao


    }

}
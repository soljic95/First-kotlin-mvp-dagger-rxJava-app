package com.example.kotlinfirstapp.di.module

import android.content.Context
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.data.CoinDatabase
import com.example.kotlinfirstapp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val context: Context) {


    @Provides
    @ApplicationScope
    fun provideContext(): Context {
        return this.context
    }

    @Provides
    @ApplicationScope
    fun provideCoinDatabase(context: Context): CoinDatabase {
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
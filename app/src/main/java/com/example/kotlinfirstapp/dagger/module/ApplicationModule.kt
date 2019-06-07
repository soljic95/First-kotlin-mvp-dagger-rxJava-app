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
    fun provideContext(): Context {
        return context
    }

    @Provides
    @ApplicationScope
    fun provideCoinDao(context: Context): CoinDao {
        return CoinDatabase.getDatabase(context).coinDao()
    }

    interface Exposes {
        fun getCoinDao(): CoinDao

    }

}
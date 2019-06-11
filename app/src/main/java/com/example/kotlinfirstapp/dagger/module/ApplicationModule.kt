package com.example.kotlinfirstapp.dagger.module

import android.content.Context
import com.example.kotlinfirstapp.dagger.scope.ActivityScope
import com.example.kotlinfirstapp.dagger.scope.ApplicationScope
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.data.CoinDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


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

    @Provides
    @ApplicationScope
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://chain.so/api/v2/get_info/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    interface Exposes {
        fun getCoinDao(): CoinDao

        fun getRetrofit(): Retrofit

    }

}
package com.example.kotlinfirstapp.di.module

import com.example.kotlinfirstapp.main.MainPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ActivityModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://chain.so/api/v2/get_info/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePresenter(retrofit: Retrofit): MainPresenter {
        return MainPresenter(retrofit)
    }
}
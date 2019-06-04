package com.example.kotlinfirstapp.di.module

import com.example.kotlinfirstapp.main.MainPresenter
import com.example.kotlinfirstapp.splash.SplashContract
import com.example.kotlinfirstapp.splash.SplashPresenter
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
    fun provideMainPresenter(retrofit: Retrofit): MainPresenter {
        return MainPresenter(retrofit)
    }

    @Provides
    fun provideSplashContract(retrofit: Retrofit): SplashContract.Presenter {
        return SplashPresenter(retrofit)
    }
}
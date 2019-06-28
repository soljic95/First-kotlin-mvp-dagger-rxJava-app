package com.example.kotlinfirstapp.di.module

import android.app.Activity
import android.content.Context
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.di.scope.ActivityScope
import com.example.kotlinfirstapp.main.MainContract
import com.example.kotlinfirstapp.main.MainPresenter
import com.example.kotlinfirstapp.router.Router
import com.example.kotlinfirstapp.router.RouterImpl
import com.example.kotlinfirstapp.ui.loginUser.LoginContract
import com.example.kotlinfirstapp.ui.loginUser.LoginPresenter
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserContract
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ActivityModule(val context: Context) {

    @Provides
    @ActivityScope
    fun provideActivityContext(): Context {
        return context
    }

    @Provides
    @ActivityScope
    fun provideRouter(context: Context): Router {
        return RouterImpl(context as Activity)
    }

    @Provides
    @ActivityScope
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://chain.so/api/v2/get_info/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @ActivityScope
    fun provideMainPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router): MainContract.Presenter {
        return MainPresenter(retrofit, coinDao, router)
    }

    @Provides
    @ActivityScope
    fun provideLoginPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router): LoginContract.Presenter {
        return LoginPresenter(retrofit, coinDao, router)
    }

    @Provides
    @ActivityScope
    fun provideRegisterUserPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router)
            : RegisterUserContract.Presenter {
        return RegisterUserPresenter(retrofit, coinDao, router)
    }

    interface Exposes {
        fun getRegisterUserPresenter(): RegisterUserContract.Presenter

        fun getRetrofit(): Retrofit

        fun getContext(): Context

        fun getMainPresenter(): MainContract.Presenter

        fun getLoginPresenter(): LoginContract.Presenter
    }
}
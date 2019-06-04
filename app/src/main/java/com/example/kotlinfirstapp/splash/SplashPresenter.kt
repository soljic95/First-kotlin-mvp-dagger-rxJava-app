package com.example.kotlinfirstapp.splash

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import retrofit2.Retrofit

class SplashPresenter(retrofit: Retrofit) : BasePresenter(retrofit), SplashContract.Presenter {
    private var splashView: SplashContract.View? = null


    override fun chooseScreen() {
        Log.d("marko", "choosing screen")
    }

    override fun setSplashView(splashView: SplashContract.View) {
        this.splashView = splashView
        Log.d("marko", "presenter set")
    }


}
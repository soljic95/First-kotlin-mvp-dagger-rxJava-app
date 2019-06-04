package com.example.kotlinfirstapp.splash

interface SplashContract {

    interface View {


    }

    interface Presenter {

        fun chooseScreen()
        fun setSplashView(splashView: View)
    }
}
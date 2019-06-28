package com.example.kotlinfirstapp.ui.splash

interface SplashContract {

    interface View {

    }

    interface Presenter {

        fun setView(view: View)

        fun init()

    }
}
package com.example.kotlinfirstapp.main

interface MainContract {

    interface View {
        fun onNameGot(coinName: String)
        fun onShowProgressBar()
        fun onHideProgressBar()
    }

    interface Presenter {
        fun btnClicked()
    }
}
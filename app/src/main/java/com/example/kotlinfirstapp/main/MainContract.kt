package com.example.kotlinfirstapp.main

interface MainContract {

    interface View {
        fun onDataReceived(coinName: String)
        fun onShowProgressBar()
        fun onHideProgressBar()
    }

    interface Presenter {
        fun btnClicked()
        fun setView(mainView: View)
    }
}
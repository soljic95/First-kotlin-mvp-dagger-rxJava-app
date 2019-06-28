package com.example.kotlinfirstapp.main


interface MainContract {

    interface View {


    }

    interface Presenter {
        fun setView(mainView: View)

        fun displayFragment()
    }
}
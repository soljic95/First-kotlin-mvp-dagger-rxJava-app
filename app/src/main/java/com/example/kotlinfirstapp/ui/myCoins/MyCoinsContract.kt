package com.example.kotlinfirstapp.ui.myCoins

import com.example.kotlinfirstapp.model.Data

interface MyCoinsContract {

    interface View {
        fun onCoinsReady(listOfCoins: ArrayList<Data>)

        fun onDisplayProgressBar()

        fun onHideProgressBar()

    }

    interface Presenter {
        fun setUpView(view: View)

        fun init()

    }
}
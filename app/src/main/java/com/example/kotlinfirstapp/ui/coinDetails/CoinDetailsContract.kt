package com.example.kotlinfirstapp.ui.coinDetails

interface CoinDetailsContract {

    interface View {

    }

    interface Presenter {
        fun goBackInFragment()

        fun setView(view: View)
    }
}
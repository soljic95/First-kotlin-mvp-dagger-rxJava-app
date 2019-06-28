package com.example.kotlinfirstapp.ui.searchCoins

interface SearchCoinsContract {

    interface View {

        fun onDisplayProgressBar()

        fun onHideProgressBar()

        fun onCoinReady(coinName: String)

    }

    interface Presenter {
        fun setSearchCoinsView(view: View)

        fun btnSearchClicked(coinName: String)

        fun btnDetailsClicked()
    }
}
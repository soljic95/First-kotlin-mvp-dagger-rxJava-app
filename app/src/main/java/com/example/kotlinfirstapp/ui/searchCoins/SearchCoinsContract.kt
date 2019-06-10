package com.example.kotlinfirstapp.ui.searchCoins

interface SearchCoinsContract {

    interface View {

        fun onDisplayProgressBar()

        fun onHideProgressBar()

        fun onCoinReady(coinName: String)

        fun onError()

    }

    interface Presenter {
        fun setSearchCoinsView(view: View)

        fun btnSearchClicked(coinName: String)

        fun btnDetailsClicked()

        fun hideProgressBar()

        fun displayProgressBar()
    }
}
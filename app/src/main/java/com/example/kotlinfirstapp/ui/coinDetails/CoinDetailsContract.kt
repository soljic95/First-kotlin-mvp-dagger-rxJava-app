package com.example.kotlinfirstapp.ui.coinDetails

import com.example.kotlinfirstapp.model.Data

interface CoinDetailsContract {

    interface View {
        fun onCoinPresent()

        fun onCoinNotPresent()
    }

    interface Presenter {
        fun goBackInFragment()

        fun setView(view: View)

        fun checkCoin(coin: Data?)

        fun saveCoinClicked(coinName: String, isChecked: Boolean)

    }
}
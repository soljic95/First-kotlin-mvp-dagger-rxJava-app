package com.example.kotlinfirstapp.ui.coinDetails

import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.router.Router
import retrofit2.Retrofit

class CoinDetailsPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) :
    BasePresenter(retrofit, coinDao, router), CoinDetailsContract.Presenter {

    private lateinit var view: CoinDetailsContract.View


    override fun setView(view: CoinDetailsContract.View) {
        this.view = view
    }


    override fun goBackInFragment() {
        getRouter().goBackInFragment()
    }


}
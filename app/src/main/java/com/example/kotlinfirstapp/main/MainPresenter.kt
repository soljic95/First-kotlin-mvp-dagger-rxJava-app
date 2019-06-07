package com.example.kotlinfirstapp.main

import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.router.Router
import retrofit2.Retrofit

class MainPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) : BasePresenter(retrofit, coinDao, router),
    MainContract.Presenter {
    override fun displayFragment() {
        getRouter().displayViewPagerFragment()
    }

    private var mainView: MainContract.View? = null


    override fun setView(mainView: MainContract.View) {
        this.mainView = mainView

    }


}
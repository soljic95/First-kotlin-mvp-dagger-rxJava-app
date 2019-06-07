package com.example.kotlinfirstapp.ui.searchCoins

import android.os.Bundle
import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.model.Data
import com.example.kotlinfirstapp.restApi.RestClient
import com.example.kotlinfirstapp.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class SearchCoinsPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) :
    BasePresenter(retrofit, coinDao, router), SearchCoinsContract.Presenter {


    private lateinit var view: SearchCoinsContract.View
    private lateinit var coin: Data


    override fun setSearchCoinsView(view: SearchCoinsContract.View) {
        this.view = view
    }

    override fun btnSearchClicked(coinName: String) {
        displayProgressBar()
        if (validateName(coinName)) {
            addDisposable(
                retrofit.create(RestClient::class.java).getDoge(coinName).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        Log.d("marko", result.data.price)
                        this.coin = result.data
                        view.onCoinReady(result.data.name)
                        hideProgressBar()
                    },
                        { errorMsg -> Log.d("marko", "error indeed ${errorMsg.localizedMessage}") })
            )
        }

    }

    override fun btnDetailsClicked() {
        var bundle = Bundle()
        bundle.putParcelable("coin", coin)
        getRouter().goToDetailsPage(bundle)
    }

    private fun validateName(coinName: String): Boolean {
        var isNameValid = true

        if (coinName.length < 3) {
            isNameValid = false
            hideProgressBar()

        }

        if (coinName.length > 13) {
            isNameValid = false
            hideProgressBar()
        }                                     //razmislit još ima li potrebe za ovom provjerom

        return isNameValid
    }

    private fun displayProgressBar() {
        view.onDisplayProgressBar()
    }

    private fun hideProgressBar() {
        view.onHideProgressBar()
    }
}
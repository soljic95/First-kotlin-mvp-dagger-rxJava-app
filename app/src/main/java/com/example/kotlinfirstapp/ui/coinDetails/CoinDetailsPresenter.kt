package com.example.kotlinfirstapp.ui.coinDetails

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.model.Data
import com.example.kotlinfirstapp.model.User
import com.example.kotlinfirstapp.router.Router
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class CoinDetailsPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) :
    BasePresenter(retrofit, coinDao, router), CoinDetailsContract.Presenter {


    private lateinit var view: CoinDetailsContract.View
    private lateinit var user: User

    override fun setView(view: CoinDetailsContract.View) {
        this.view = view
    }


    override fun goBackInFragment() {
        getRouter().goBackInFragment()
    }

    override fun checkCoin(coin: Data?) {
        addDisposable(getCoinDao().getLoggedUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { user ->
                this.user = user
                if (checkIfCoinIsSaved(user, coin!!.name)) {
                    view.onCoinPresent()
                }
            })
    }

    private fun checkIfCoinIsSaved(user: User, coinName: String): Boolean {
        var isCoinPresent = false
        if (user.userCoins.contains(coinName)) isCoinPresent = true

        return isCoinPresent
    }

    override fun saveCoinClicked(coinName: String, isChecked: Boolean) {
        when (isChecked) {
            true -> {
                user.userCoins.remove(coinName)
                view.onCoinNotPresent()
            }
            false -> {
                if (!user.userCoins.contains(coinName)) {
                    user.userCoins.add(coinName)
                    view.onCoinPresent()
                }

            }

        }
        addDisposable(Completable.fromAction { getCoinDao().logUser(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ Log.d("marko", "user updated") },
                { errorMsg -> Log.d("marko", "an error occured ${errorMsg.localizedMessage}") })
        )
    }


}
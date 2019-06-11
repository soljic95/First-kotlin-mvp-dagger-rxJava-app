package com.example.kotlinfirstapp.ui.myCoins

import android.os.Bundle
import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.model.Data
import com.example.kotlinfirstapp.model.User
import com.example.kotlinfirstapp.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MyCoinsPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) :
    BasePresenter(retrofit, coinDao, router), MyCoinsContract.Presenter {


    private lateinit var view: MyCoinsContract.View
    private lateinit var user: User
    private var listOfCoins: ArrayList<Data> = ArrayList()


    override fun setUpView(view: MyCoinsContract.View) {
        this.view = view
    }

    override fun init() {
        view.onDisplayProgressBar()
        addDisposable(
            getCoinDao().getLoggedUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    this.user = user
                    if (user.userCoins.size > 1) {
                        getCoinList()
                    } else view.onHideProgressBar()
                },
                    { errorMsg -> Log.d("marko", "error happend ${errorMsg.localizedMessage}") })
        )
    }


    override fun hideProgressBar() {
        view.onHideProgressBar()
    }

    private fun getCoinList() {
        var i = 0
        for (coinName in user.userCoins) {
            i++
            if (coinName != "") {
                addDisposable(
                    getClientAdapter().getCoin(coinName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ response ->
                            if (!listOfCoins.contains(response.data) && response.data.name != "Unlisted") {
                                listOfCoins.add(response.data)
                            }
                            if (i == user.userCoins.size) view.onCoinsReady(listOfCoins)
                        }, {
                            Log.d("marko", it.localizedMessage)
                            view.onHideProgressBar()
                        })
                )

            }

        }
    }

    override fun onRecyclerItemClicked(bundle: Bundle) {
        getRouter().goToDetailsPage(bundle)
    }


}


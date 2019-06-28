package com.example.kotlinfirstapp.main

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) : BasePresenter(retrofit, coinDao, router),
    MainContract.Presenter {
    private var mainView: MainContract.View? = null

    override fun btnClicked() {
        mainView?.onShowProgressBar()
        addDisposable(getClientAdapter().getDoge()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { succes ->
                    mainView?.onHideProgressBar()
                    mainView?.onDataReceived(succes.data.name)
                },
                { error -> Log.d("marko", error.localizedMessage) }
            ))


    }


    override fun setView(mainView: MainContract.View) {
        this.mainView = mainView

    }


}
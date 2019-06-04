package com.example.kotlinfirstapp.main

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainPresenter(retrofit: Retrofit) : BasePresenter(retrofit), MainContract.Presenter {

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

    fun setMainView(mainView: MainContract.View) {
        this.mainView = mainView
    }

}
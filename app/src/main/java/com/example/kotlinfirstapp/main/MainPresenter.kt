package com.example.kotlinfirstapp.main

import android.util.Log
import com.example.kotlinfirstapp.restApi.RestClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainPresenter(retrofit: Retrofit) : MainContract.Presenter {

    private var mainView: MainContract.View? = null
    val callAdapter = retrofit.create(RestClient::class.java)

    override fun btnClicked() {
        mainView?.onShowProgressBar()
        callAdapter.getDoge().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { succes ->
                    mainView?.onHideProgressBar()
                    mainView?.onNameGot(succes.data.name)
                },
                { error -> Log.d("marko", error.localizedMessage) }
            )
    }

    fun setMainView(mainView: MainContract.View) {
        this.mainView = mainView
    }

}
package com.example.kotlinfirstapp.base

import com.example.kotlinfirstapp.restApi.RestClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit

abstract class BasePresenter constructor(val retrofit: Retrofit) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var adapterCall = retrofit.create(RestClient::class.java)

    fun getClientAdapter(): RestClient {
        return adapterCall
    }

    fun deactivate() {
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}
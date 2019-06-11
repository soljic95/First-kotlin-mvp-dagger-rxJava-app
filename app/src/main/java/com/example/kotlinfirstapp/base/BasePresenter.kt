package com.example.kotlinfirstapp.base

import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.restApi.RestClient
import com.example.kotlinfirstapp.router.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit

abstract class BasePresenter constructor(
    private val retrofit: Retrofit,
    private val coinDao: CoinDao,
    private val router: Router
) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var adapterCall = retrofit.create(RestClient::class.java)


    protected fun getClientAdapter(): RestClient {
        return adapterCall
    }

    fun deactivate() {
        compositeDisposable.clear()
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun getCoinDao(): CoinDao {
        return coinDao
    }

    protected fun getRouter(): Router {
        return router
    }

    fun activate() {
        //do something
    }
}
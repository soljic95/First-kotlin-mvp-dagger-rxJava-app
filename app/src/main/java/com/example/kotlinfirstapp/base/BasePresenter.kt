package com.example.kotlinfirstapp.base

import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.restApi.RestClient
import com.example.kotlinfirstapp.router.Router
import com.example.kotlinfirstapp.router.RouterImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit

abstract class BasePresenter constructor(
    val retrofit: Retrofit,
    private val coinDao: CoinDao,
    private val router: Router
) {

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

    fun getCoinDao(): CoinDao {
        return coinDao
    }

    fun getRouter(): Router {
        return router
    }
}
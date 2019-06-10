package com.example.kotlinfirstapp.restApi

import com.example.kotlinfirstapp.model.CoinResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestClient {
    @GET("{coinName}")
    fun getCoin(@Path("coinName") coinName: String): Single<CoinResponse>

    @GET("{coinName}")
    fun getCoinList(@Path("coinName") coinName: String): Observable<CoinResponse>
}
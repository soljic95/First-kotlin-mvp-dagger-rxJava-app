package com.example.kotlinfirstapp.restApi

import com.example.kotlinfirstapp.model.CoinResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestClient {
    @GET("{coinName}")
    fun getDoge(@Path("coinName") coinName: String): Single<CoinResponse>
}
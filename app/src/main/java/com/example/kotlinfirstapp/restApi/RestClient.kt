package com.example.kotlinfirstapp.restApi

import com.example.kotlinfirstapp.model.DogeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RestClient {
    @GET("DOGE")
    fun getDoge(): Single<DogeResponse>
}
package com.apps.nabungemas.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CurrencyApi {
    private val BASE_URL = "https://exchange-rates.abstractapi.com/v1/"
    private val moshi= Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: CurrencyApiService by lazy {
        retrofit.create(CurrencyApiService::class.java)
    }
}
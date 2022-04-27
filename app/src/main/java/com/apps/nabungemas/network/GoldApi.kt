package com.apps.nabungemas.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object GoldApi {
    private val BASE_URL = "https://www.goldapi.io/api/"
    private val BASE_URL_CURRENCY = "https://v6.exchangerate-api.com/"
    private val moshi=Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
    private val retrofitCurrency = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL_CURRENCY)
        .build()
    val retrofitService: GoldApiService by lazy {
        retrofit.create(GoldApiService::class.java)
    }
    val retrofitServiceCurrency: CurrencyApiService by lazy {
        retrofitCurrency.create(CurrencyApiService::class.java)
    }
}

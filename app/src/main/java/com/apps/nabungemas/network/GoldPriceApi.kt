package com.apps.nabungemas.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object GoldPriceApi {
    private val BASE_URL = "https://scraping-antam.vercel.app/"
    private val moshi= Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(client)
        .build()
    val retrofitService: GoldPriceApiService by lazy {
        retrofit.create(GoldPriceApiService::class.java)
    }
}
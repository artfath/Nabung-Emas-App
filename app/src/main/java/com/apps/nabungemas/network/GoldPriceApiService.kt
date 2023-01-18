package com.apps.nabungemas.network

import com.apps.nabungemas.model.GoldUpdatePrice
import retrofit2.http.GET

interface GoldPriceApiService {
    @GET("/product")
    suspend fun getPrice(): GoldUpdatePrice.GoldResponse
}
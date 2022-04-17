package com.apps.nabungemas.network

import com.apps.nabungemas.model.Currencies
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("live/")
    suspend fun getCurrency(
        @Query("api_key") token: String?,
        @Query("base") base: String?,
        @Query("target")target: String?
    ): Currencies
}
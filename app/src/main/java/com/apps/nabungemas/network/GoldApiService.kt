package com.apps.nabungemas.network

import com.apps.nabungemas.model.GoldPrice
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GoldApiService {
    @GET("{id}/USD")
    suspend fun getPrice(
        @Path("id") code: String?,
        @Header("x-access-token") token: String?
    ): GoldPrice
}
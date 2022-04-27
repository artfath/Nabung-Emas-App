package com.apps.nabungemas.network

import com.apps.nabungemas.model.Currencies
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {
    @GET("v6/{access_key}/pair/{base}/{target}")
    suspend fun getCurrency(
        @Path("access_key") token: String?,
        @Path("base") base: String?,
        @Path("target")target: String?
    ): Currencies
}
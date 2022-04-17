package com.apps.nabungemas.model

import com.squareup.moshi.Json

data class Currencies(
    val base: String,
    @Json(name = "exchange_rates")
    val exchangeRates: ExchangeRates,
    @Json(name = "last_updated")
    val time: Int
)

data class ExchangeRates(
    @Json(name = "IDR")
    val idr: Double
)
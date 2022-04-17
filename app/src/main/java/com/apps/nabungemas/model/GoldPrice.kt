package com.apps.nabungemas.model

import com.squareup.moshi.Json

data class GoldPrice(
    val currency: String,
    val metal: String,
    @Json(name = "prev_close_price")
    val prevPrice: Double,

    @Json(name = "price")
    val pricePerOunce: Double,

    @Json(name = "ch")
    val priceDifferent: Double,

    @Json(name = "price_gram_24k")
    val priceGram24k: Double,

    @Json(name = "timestamp")
    val time: Int

)
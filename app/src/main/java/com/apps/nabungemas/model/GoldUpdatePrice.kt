package com.apps.nabungemas.model

import com.squareup.moshi.Json

data class GoldUpdatePrice(

    @Json(name = "name")
    val name: String?,

    @Json(name = "gold_price")
    val goldPrice: String?,

    @Json(name = "last_price")
    val lastPrice: String?,

    @Json(name = "change_gold")
    val change: String?,

    @Json(name = "timestamp")
    val time: Long
){
    data class GoldResponse(
        @Json(name = "data")
        val data: MutableList<GoldUpdatePrice>?
    )
}

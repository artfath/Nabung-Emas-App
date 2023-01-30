package com.apps.nabungemas.model

import com.squareup.moshi.Json

data class Currencies(
    @Json(name = "time_last_update_unix")
    val time: Long,
    @Json(name = "base_code")
    val baseCode: String,
    @Json(name = "target_code")
    val targetCode: String,
    @Json(name = "conversion_rate")
    val conversionRate: Double

)




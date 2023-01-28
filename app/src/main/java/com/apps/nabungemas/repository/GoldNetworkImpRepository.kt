package com.apps.nabungemas.repository

import com.apps.nabungemas.model.GoldUpdatePrice
import com.apps.nabungemas.network.GoldPriceApiService

class GoldNetworkImpRepository(private val goldService:GoldPriceApiService):GoldRepository {
    override suspend fun getPrice(): GoldUpdatePrice.GoldResponse =goldService.getPrice()
}
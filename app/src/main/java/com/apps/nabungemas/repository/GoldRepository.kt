package com.apps.nabungemas.repository

import com.apps.nabungemas.model.GoldUpdatePrice

interface GoldRepository {
    suspend fun getPrice(): GoldUpdatePrice.GoldResponse
}
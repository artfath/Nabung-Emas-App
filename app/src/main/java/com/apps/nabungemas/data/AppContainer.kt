package com.apps.nabungemas.data


import android.content.Context
import com.apps.nabungemas.network.GoldPriceApi
import com.apps.nabungemas.repository.GoldNetworkImpRepository
import com.apps.nabungemas.repository.GoldRepository
import com.apps.nabungemas.repository.TransactionsImpRepository
import com.apps.nabungemas.repository.TransactionsRepository

interface AppContainer {
    val transactionsRepository:TransactionsRepository
    val goldRepository:GoldRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val transactionsRepository: TransactionsRepository by lazy {
        TransactionsImpRepository(context = context,TransactionRoomDatabase.getDatabase(context).transactionDao())
    }
    override val goldRepository: GoldRepository by lazy{
        GoldNetworkImpRepository(GoldPriceApi.retrofitService)
    }


}
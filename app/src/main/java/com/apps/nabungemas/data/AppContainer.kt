package com.apps.nabungemas.data


import android.content.Context
import com.apps.nabungemas.repository.TransactionsImpRepository
import com.apps.nabungemas.repository.TransactionsRepository

interface AppContainer {
    val transactionsRepository:TransactionsRepository
}

class DefaultAppContainer(private val contex: Context) : AppContainer {
    override val transactionsRepository: TransactionsRepository by lazy {
        TransactionsImpRepository(TransactionRoomDatabase.getDatabase(contex).transactionDao())
    }


}
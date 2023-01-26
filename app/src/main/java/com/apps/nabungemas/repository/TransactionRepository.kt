package com.apps.nabungemas.repository

import com.apps.nabungemas.data.GoldCurrencyTable
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionTable
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    suspend fun insertTransactions(item: TransactionTable)

    suspend fun updateTransactions(item: TransactionTable)

    suspend fun deleteTransactions(item: TransactionTable)

    fun getTransactions(): Flow<List<TransactionTable>>
    fun getTransactionId(id:Int):Flow<TransactionTable>

    suspend fun insertSaving(item: SavingTable)

    suspend fun updateSaving(item: SavingTable)

    fun getAllSaving(): Flow<List<SavingTable>>

    suspend fun getSaving(categorySaving:String):Long?

    suspend fun findSaving(categorySaving:String): TransactionTable?

    suspend fun findCategorySaving(categorySaving:String): SavingTable?

    fun getTotalSaving(): Flow<Long?>

    fun getTotalTarget(): Flow<Long?>

    fun getPercentage(): Flow<Double?>

    suspend fun findGoldCurrency(): GoldCurrencyTable?

    suspend fun insertGoldCurrency(item: GoldCurrencyTable)

    fun getGoldCurrency(): Flow<GoldCurrencyTable>
}
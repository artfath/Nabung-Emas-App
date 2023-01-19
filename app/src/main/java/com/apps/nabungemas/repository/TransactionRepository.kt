package com.apps.nabungemas.repository

import com.apps.nabungemas.data.GoldCurrenncyTable
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionTable
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    suspend fun insertData(item: TransactionTable)

    suspend fun updateData(item: TransactionTable)

    suspend fun deleteData(item: TransactionTable)

    fun getTransactions(): Flow<List<TransactionTable>>

    suspend fun insertSaving(item: SavingTable)

    suspend fun updateSaving(item: SavingTable)

    fun getAllSaving(): Flow<List<SavingTable>>

    suspend fun getSaving(categorySaving:String):Long?

    suspend fun findSaving(categorySaving:String): TransactionTable?

    suspend fun findCategorySaving(categorySaving:String): SavingTable?

    fun getTotalSaving(): Flow<Long?>

    fun getTotalTarget(): Flow<Long?>

    fun getPercentage(): Flow<Double?>

    suspend fun findGoldCurrency(): GoldCurrenncyTable?

    suspend fun insertGoldCurrency(item: GoldCurrenncyTable)

    fun getGoldCurrency(): Flow<GoldCurrenncyTable>
}
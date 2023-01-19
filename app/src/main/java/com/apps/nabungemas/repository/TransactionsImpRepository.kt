package com.apps.nabungemas.repository

import com.apps.nabungemas.data.GoldCurrenncyTable
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionDao
import com.apps.nabungemas.data.TransactionTable
import kotlinx.coroutines.flow.Flow

class TransactionsImpRepository(private val transactionDao: TransactionDao):TransactionsRepository {
    override suspend fun insertData(item: TransactionTable) =
        transactionDao.insertData(item)


    override suspend fun updateData(item: TransactionTable) =
        transactionDao.updateData(item)


    override suspend fun deleteData(item: TransactionTable) =
        transactionDao.deleteData(item)


    override fun getTransactions(): Flow<List<TransactionTable>> =
         transactionDao.getTransactions()


    override suspend fun insertSaving(item: SavingTable) =
        transactionDao.insertSaving(item)


    override suspend fun updateSaving(item: SavingTable) =
        transactionDao.updateSaving(item)


    override fun getAllSaving(): Flow<List<SavingTable>> = transactionDao.getAllSaving()


    override suspend fun getSaving(categorySaving: String): Long? =
         transactionDao.getSaving(categorySaving)


    override suspend fun findSaving(categorySaving: String): TransactionTable? = transactionDao.findSaving(categorySaving)


    override suspend fun findCategorySaving(categorySaving: String): SavingTable? = transactionDao.findCategorySaving(categorySaving)


    override fun getTotalSaving(): Flow<Long?> = transactionDao.getTotalSaving()


    override fun getTotalTarget(): Flow<Long?> = transactionDao.getTotalTarget()


    override fun getPercentage(): Flow<Double?> = transactionDao.getPercentage()


    override suspend fun findGoldCurrency(): GoldCurrenncyTable?= transactionDao.findGoldCurrency()


    override suspend fun insertGoldCurrency(item: GoldCurrenncyTable) =
        transactionDao.insertGoldCurrency(item)


    override fun getGoldCurrency(): Flow<GoldCurrenncyTable> = transactionDao.getGoldCurrency()


}
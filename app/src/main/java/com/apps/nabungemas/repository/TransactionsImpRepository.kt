package com.apps.nabungemas.repository

import com.apps.nabungemas.data.*
import kotlinx.coroutines.flow.Flow

class TransactionsImpRepository(private val transactionDao: TransactionDao):TransactionsRepository {
    override suspend fun insertTransactions(item: TransactionTable) =
        transactionDao.insertTransactions(item)


    override suspend fun updateTransactions(item: TransactionTable) =
        transactionDao.updateTransactions(item)


    override suspend fun deleteTransactions(item: TransactionTable) =
        transactionDao.deleteTransactions(item)


    override fun getTransactions(): Flow<List<TransactionTable>> =
         transactionDao.getTransactions()

    override fun getTransactionId(id: Int): Flow<TransactionTable> =
        transactionDao.getTransactionId(id)


    override suspend fun insertSaving(item: SavingTable) =
        transactionDao.insertSaving(item)


    override suspend fun updateSaving(item: SavingTable) =
        transactionDao.updateSaving(item)

    override suspend fun deleteSaving(item: SavingTable)=transactionDao.deleteSaving(item)

    override fun getAllSaving(): Flow<List<SavingTable>> = transactionDao.getAllSaving()


    override suspend fun getSaving(categorySaving: String): Long? =
         transactionDao.getSaving(categorySaving)


    override suspend fun findSaving(categorySaving: String): TransactionTable? = transactionDao.findSaving(categorySaving)


    override suspend fun findCategorySaving(categorySaving: String): SavingTable? = transactionDao.findCategorySaving(categorySaving)


    override fun getTotalSaving(): Flow<Long?> = transactionDao.getTotalSaving()


    override fun getTotalTarget(): Flow<Long?> = transactionDao.getTotalTarget()


    override fun getPercentage(): Flow<Double?> = transactionDao.getPercentage()


    override suspend fun findGoldCurrency(): GoldCurrencyTable?= transactionDao.findGoldCurrency()


    override suspend fun insertGoldCurrency(item: GoldCurrencyTable) =
        transactionDao.insertGoldCurrency(item)


    override fun getGoldCurrency(): Flow<GoldCurrencyTable> = transactionDao.getGoldCurrency()


}
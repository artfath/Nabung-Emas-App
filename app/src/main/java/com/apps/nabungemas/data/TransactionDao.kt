package com.apps.nabungemas.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransactions(item: TransactionTable)

    @Update
    suspend fun updateTransactions(item: TransactionTable)

    @Delete
    suspend fun deleteTransactions(item: TransactionTable)

    @Query("SELECT * FROM `transaction` ORDER BY category ASC")
    fun getTransactions(): Flow<List<TransactionTable>>

    @Query("SELECT * FROM `transaction` WHERE id = :id")
    fun getTransactionId(id: Int): Flow<TransactionTable>

/*
*Saving Block
* */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSaving(item: SavingTable)

    @Update
    suspend fun updateSaving(item: SavingTable)

    @Delete
    suspend fun deleteSaving(item: SavingTable)

    @Query("SELECT * FROM saving ORDER BY category ASC")
    fun getAllSaving(): Flow<List<SavingTable>>

    @Query("SELECT SUM(price) FROM `transaction` WHERE category = :categorySaving")
    suspend fun getSaving(categorySaving: String): Long?

    @Query("SELECT * FROM `transaction` WHERE category = :categorySaving LIMIT 1")
    suspend fun findSaving(categorySaving: String): TransactionTable?

    @Query("SELECT * FROM `saving` WHERE category = :categorySaving LIMIT 1")
    suspend fun findCategorySaving(categorySaving: String): SavingTable?


    @Query("SELECT SUM(total_saving) FROM `saving` ")
    fun getTotalSaving(): Flow<Long?>

    @Query("SELECT SUM(target) FROM `saving` ")
    fun getTotalTarget(): Flow<Long?>

    @Query("SELECT AVG(percentage) FROM `saving` ")
    fun getPercentage(): Flow<Double?>

    @Query("SELECT * FROM `gold_currency`")
    suspend fun findGoldCurrency(): GoldCurrencyTable?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGoldCurrency(item: GoldCurrencyTable)

    @Query("SELECT * FROM `gold_currency` ORDER BY id DESC")
    fun getGoldCurrency(): Flow<GoldCurrencyTable>

    @Query("SELECT * FROM `gold_currency` GROUP BY date_currency LIMIT 7")
    fun getGoldWeek(): Flow<List<GoldCurrencyTable>>
}
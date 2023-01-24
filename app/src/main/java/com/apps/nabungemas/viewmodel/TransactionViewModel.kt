package com.apps.nabungemas.viewmodel

import android.content.ClipData
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionDao
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.repository.TransactionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: TransactionsRepository) : ViewModel() {

    val allTransaction: LiveData<List<TransactionTable>> =
        repository.getTransactions().asLiveData()

    //    val savingnikah:LiveData<Long> = transtionDao.getSaving("nikah")
    val allSaving: LiveData<List<SavingTable>> = repository.getAllSaving().asLiveData()
    private var _saving = MutableLiveData<Long>()
//    val data = transtionDao.findCategorySaving("Tabungan Menikah").asLiveData()

    private var _category = MutableLiveData<Boolean>()
    val category: LiveData<Boolean> = _category

    val allTransactionState: Flow<List<TransactionTable>> = repository.getTransactions()
    val allSavingState: Flow<List<SavingTable>> = repository.getAllSaving()

    var transactionUiState by mutableStateOf(TransactionUiState())
        private set



    private fun insertTransaction(transaction: TransactionTable) {
        viewModelScope.launch {
            repository.insertData(transaction)
        }
    }

    fun deleteTransaction() {
        viewModelScope.launch { }
    }

    private fun insertSaving(saving: SavingTable) {
        viewModelScope.launch {
            repository.insertSaving(saving)
        }
    }


    fun getSaving(categorySaving: String) {

//            try{
//                val result = transtionDao.getSaving(categorySaving)
//                _saving  = result
//            }catch (e:Exception){
//                Log.e("saving error",e.stackTrace.toString())
//                _saving.value = 0
//            }


//        if(total == null ){
//            return 0
//        }else{
//            return total
//        }
    }

    private fun updateSaving(saving: SavingTable) {
        viewModelScope.launch {
            repository.updateSaving(saving)
        }
    }


    private fun TransactionTableDetails.getNewTransaction(
    ): TransactionTable {
        return TransactionTable(
            savingCategory = savingCategory,
            time = time,
            goldPrice = goldPrice.toLong(),
            goldQuantity = goldQuantity.toDouble(),
            product = product
        )
    }

    fun addNewTransaction() {
//        val newTransaction = getNewTransaction(category, date, price, quantity, product)
        insertTransaction(transactionUiState.transactionDetails.getNewTransaction())
        viewModelScope.launch {
            try {
                val dataCategory = repository.findCategorySaving(transactionUiState.transactionDetails.savingCategory)
                if (dataCategory != null) {
                    addNewSaving(transactionUiState.transactionDetails.savingCategory,
                        dataCategory.target.toString())
                }
            } catch (e: Exception) {

            }

        }

    }

    fun isEntryValid(
        uiState: TransactionTableDetails =transactionUiState.transactionDetails
    ): Boolean {
        return if (uiState.savingCategory.isBlank() || uiState.time.isBlank() ||
            uiState.goldPrice.isBlank() || uiState.goldQuantity.isBlank() ||
            uiState.product.isBlank()) {
            false
        }else{
            true
        }

    }

    private fun getNewSaving(
        catagory: String,
        target: String,
        total: Long,
        percentage: Double
    ): SavingTable {
        return SavingTable(
            savingCategory = catagory,
            target = target.toLong(),
            totalSaving = total,
            percentage = percentage,
        )
    }



    private fun getUpdatedSaving(
        itemId: Int,
        catagory: String,
        target: String,
        total: Long,
        percentage: Double
    ): SavingTable {
        return SavingTable(
            id = itemId,
            savingCategory = catagory,
            target = target.toLong(),
            totalSaving = total,
            percentage = percentage,
        )
    }


    fun addNewSaving(
        category: String,
        target: String
    ) {
        var totalSaving: Long?
        var percentage: Double
        var dataCategory: SavingTable?
        viewModelScope.launch {
            try {
                dataCategory = repository.findCategorySaving(category)
                Log.d("date category", dataCategory.toString())

                if (dataCategory != null) {
                    val find = repository.findSaving(category)
                    Log.d("find saving", find.toString())

                    if (find != null) {
                        totalSaving = repository.getSaving(category)
                        Log.d("transaction", totalSaving!!.toString())

                        if (totalSaving != null) {
                            percentage =
                                (totalSaving!!.toDouble().div(target.toDouble()).times(100))
                            Log.d("percentage", percentage.toString())

                            if (dataCategory!!.savingCategory == category) {
                                val updatedItem =
                                    getUpdatedSaving(
                                        dataCategory!!.id,
                                        category,
                                        target,
                                        totalSaving!!,
                                        percentage
                                    )
                                Log.d("update", updatedItem.toString())
                                updateSaving(updatedItem)
                                Log.d("update", "update saving")
                            } else {
                                val newSaving =
                                    getNewSaving(category, target, totalSaving!!, percentage)
                                insertSaving(newSaving)
                                Log.d("update", "insert saving")
                            }
                        } else {
                            if (dataCategory!!.savingCategory == category) {
                                val updatedItem =
                                    getUpdatedSaving(
                                        dataCategory!!.id,
                                        category,
                                        target,
                                        0,
                                        0.0
                                    )
                                Log.d("update", updatedItem.toString())
                                updateSaving(updatedItem)
                                Log.d("update", "update saving")
                            } else {
                                val newSaving =
                                    getNewSaving(category, target, 0, 0.0)
                                insertSaving(newSaving)
                                Log.d("update", "insert saving")
                            }
                        }

                    } else {
                        if (dataCategory!!.savingCategory == category) {
                            val updatedItem =
                                getUpdatedSaving(
                                    dataCategory!!.id,
                                    category,
                                    target,
                                    0,
                                    0.0
                                )
                            Log.d("update", updatedItem.toString())
                            updateSaving(updatedItem)
                            Log.d("update", "update saving")
                        } else {
                            val newSaving =
                                getNewSaving(category, target, 0, 0.0)
                            insertSaving(newSaving)
                            Log.d("update", "insert saving")
                        }
                    }

                } else {
                    val newSaving = getNewSaving(category, target, 0, 0.0)
                    insertSaving(newSaving)
                    Log.d("nocategory", "no category")
                }
//                totalSaving = saving.value!!

            } catch (e: Exception) {
                dataCategory =
                    SavingTable(savingCategory = "", percentage = 0.0, target = 0, totalSaving = 0)
                totalSaving = 0
//                val newSaving = getNewSaving(catagory, target, 0, 0.0)
//                insertSaving(newSaving)
//                Log.d("nocategory", "no category")

            }


//        isCategoryExist(catagory)


        }


    }

    fun updateUiState(transactionDetails: TransactionTableDetails) {
        transactionUiState =
            TransactionUiState(transactionDetails = transactionDetails, isEntryValid = isEntryValid(transactionDetails))
    }

}

data class TransactionUiState(
    val transactionDetails: TransactionTableDetails = TransactionTableDetails(),
    val isEntryValid: Boolean = false
)

data class TransactionTableDetails(
    val id: Int=0,
    val time:String="",
    val savingCategory:String="",
    val goldPrice:String="",
    val goldQuantity:String="",
    val product:String=""

)

package com.apps.nabungemas.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.repository.TransactionsRepository
import kotlinx.coroutines.flow.*
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

    var addTransactionUiState by mutableStateOf(TransactionUiState())
        private set

    var addSavingUiState by mutableStateOf(SavingUiState())
        private set

    var transactionUiState: StateFlow<TransactionTable> =
        repository.getTransactionId(0)
            .filterNotNull()
            .map {
                it
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = TransactionTable()
            )


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


    /**
     * Transaction block
     */


    fun addNewTransaction() {
//        val newTransaction = getNewTransaction(category, date, price, quantity, product)
        insertTransaction(addTransactionUiState.transactionDetails.getNewTransaction())
        viewModelScope.launch {
            try {
                val dataCategory =
                    repository.findCategorySaving(addTransactionUiState.transactionDetails.savingCategory)
                val target = dataCategory?.target ?: 0L

                addNewSavings(
                    addTransactionUiState.transactionDetails.savingCategory,
                    target.toString()
                )

            } catch (e: Exception) {

            }
        }
    }

    fun deleteTransaction(transaction: TransactionTable) {
        viewModelScope.launch {
            repository.deleteTransactions(transaction)
            try {
                val dataCategory =
                    repository.findCategorySaving(transaction.savingCategory)
                val target = dataCategory?.target ?: 0L

                addNewSavings(
                    transaction.savingCategory,
                    target.toString()
                )

            } catch (e: Exception) {

            }
        }
    }

    private fun insertTransaction(transaction: TransactionTable) {
        viewModelScope.launch {
            repository.insertTransactions(transaction)
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


    /**
     * Saving block
     */


//    fun addNewSaving(
//        category: String = savingUiState.savingDetails.savingCategory,
//        target: String = savingUiState.savingDetails.target
//    ) {
//        var totalSaving: Long
//        var percentage: Double
//        var dataCategory: SavingTable?
//        viewModelScope.launch {
//            try {
//                dataCategory = repository.findCategorySaving(category)
//                Log.d("date category", dataCategory.toString())
//
//                if (dataCategory != null) {
//                    val find = repository.findSaving(category)
//                    Log.d("find saving", find.toString())
//
//                    if (find != null) {
//                        totalSaving = repository.getSaving(category) ?: 0
//                        Log.d("transaction", totalSaving.toString())
//
//                        if (totalSaving != 0L) {
//                            percentage =
//                                (totalSaving.toDouble().div(target.toDouble()).times(100))
//                            Log.d("percentage", percentage.toString())
//
//                            if (dataCategory!!.savingCategory == category) {
//                                val updatedItem =
//                                    getUpdatedSaving(
//                                        dataCategory!!.id,
//                                        category,
//                                        target,
//                                        totalSaving,
//                                        percentage
//                                    )
//                                Log.d("update", updatedItem.toString())
//                                updateSaving(updatedItem)
//                                Log.d("update", "update saving")
//                            } else {
//                                val newSaving =
//                                    getNewSaving(category, target, totalSaving, percentage)
//                                insertSaving(newSaving)
//                                Log.d("update", "insert saving")
//                            }
//                        } else {
//                            if (dataCategory!!.savingCategory == category) {
//                                val updatedItem =
//                                    getUpdatedSaving(
//                                        dataCategory!!.id,
//                                        category,
//                                        target,
//                                        0,
//                                        0.0
//                                    )
//                                Log.d("update", updatedItem.toString())
//                                updateSaving(updatedItem)
//                                Log.d("update", "update saving")
//                            } else {
//                                val newSaving =
//                                    getNewSaving(category, target, 0, 0.0)
//                                insertSaving(newSaving)
//                                Log.d("update", "insert saving")
//                            }
//                        }
//
//                    } else {
//                        if (dataCategory!!.savingCategory == category) {
//                            val updatedItem =
//                                getUpdatedSaving(
//                                    dataCategory!!.id,
//                                    category,
//                                    target,
//                                    0,
//                                    0.0
//                                )
//                            Log.d("update", updatedItem.toString())
//                            updateSaving(updatedItem)
//                            Log.d("update", "update saving")
//                        } else {
//                            val newSaving =
//                                getNewSaving(category, target, 0, 0.0)
//                            insertSaving(newSaving)
//                            Log.d("update", "insert saving")
//                        }
//                    }
//
//                } else {
//                    totalSaving = repository.getSaving(category) ?: 0
//                    percentage =
//                        (totalSaving.toDouble().div(target.toDouble()).times(100))
//                    val newSaving = getNewSaving(category, target, totalSaving, 0.0)
//                    insertSaving(newSaving)
//                    Log.d("nocategory", "no category")
//                }
////                totalSaving = saving.value!!
//
//            } catch (e: Exception) {
//                dataCategory =
//                    SavingTable(savingCategory = "", percentage = 0.0, target = 0, totalSaving = 0)
//                totalSaving = 0
//
//            }
//
//
//        }
//
//
//    }
    fun addNewSavings(
        category: String = addSavingUiState.savingDetails.savingCategory,
        target: String = addSavingUiState.savingDetails.target
    ) {
        viewModelScope.launch {
            try {
                val dataCategory = repository.findCategorySaving(category)
                val totalSaving = repository.getSaving(category) ?: 0
                val percentage = if(target.toDouble() == 0.0){
                     0.0
                }else{
                    (totalSaving.toDouble().div(target.toDouble()).times(100))
                }

                if (dataCategory?.savingCategory.isNullOrEmpty()) {
                    val newSaving = getNewSaving(category, target, totalSaving, percentage)
                    insertSaving(newSaving)
                } else {
                    val updatedItem =
                        getUpdatedSaving(
                            dataCategory!!.id,
                            category,
                            target,
                            totalSaving,
                            percentage
                        )
                    updateSaving(updatedItem)
                }
            } catch (e: Exception) {

            }
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

    private fun updateSaving(saving: SavingTable) {
        viewModelScope.launch {
            repository.updateSaving(saving)
        }
    }

    private fun insertSaving(saving: SavingTable) {
        viewModelScope.launch {
            repository.insertSaving(saving)
        }
    }


    fun updateAddTransactionUiState(transactionDetails: TransactionTableDetails) {
        addTransactionUiState =
            TransactionUiState(
                transactionDetails = transactionDetails,
                isEntryValid = isEntryTransactionValid(transactionDetails)
            )
    }

    fun isEntryTransactionValid(
        uiState: TransactionTableDetails = addTransactionUiState.transactionDetails
    ): Boolean {
        return if (uiState.savingCategory.isBlank() || uiState.time.isBlank() ||
            uiState.goldPrice.isBlank() || uiState.goldQuantity.isBlank() ||
            uiState.product.isBlank()
        ) {
            false
        } else {
            true
        }
    }

    fun updateSavingUiState(savingDetails: SavingDetails) {
        addSavingUiState = SavingUiState(
            savingDetails = savingDetails,
            isEntryValid = isEntrySavingValid(savingDetails)
        )
    }

    fun isEntrySavingValid(
        uiState: SavingDetails = addSavingUiState.savingDetails
    ): Boolean {
        return if (uiState.savingCategory.isBlank() || uiState.target.isBlank()) {
            false
        } else {
            true
        }
    }

}


data class TransactionUiState(
    val transactionDetails: TransactionTableDetails = TransactionTableDetails(),
    val isEntryValid: Boolean = false
)

data class TransactionTableDetails(
    val id: Int = 0,
    val time: String = "",
    val savingCategory: String = "",
    val goldPrice: String = "",
    val goldQuantity: String = "",
    val product: String = ""
)

data class SavingUiState(
    val savingDetails: SavingDetails = SavingDetails(),
    val isEntryValid: Boolean = false
)

data class SavingDetails(
    val savingCategory: String = "",
    val target: String = "",
)

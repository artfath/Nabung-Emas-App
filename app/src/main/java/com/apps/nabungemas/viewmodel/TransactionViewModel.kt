package com.apps.nabungemas.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.repository.TransactionsRepository
import com.apps.nabungemas.utils.Time
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: TransactionsRepository) : ViewModel() {


    val allTransactionState: Flow<List<TransactionTable>> = repository.getTransactions()
    val allSavingState: Flow<List<SavingTable>> = repository.getAllSaving()

    var addTransactionUiState by mutableStateOf(TransactionUiState())
        private set

    var addSavingUiState by mutableStateOf(SavingUiState())
        private set


    /**
     * Transaction block
     */


    fun addNewTransaction() {
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


    fun addNewSavings(
        category: String = addSavingUiState.savingDetails.savingCategory,
        target: String = addSavingUiState.savingDetails.target
    ) {
        viewModelScope.launch {
            try {
                val dataCategory = repository.findCategorySaving(category)
                val totalSaving = repository.getSaving(category) ?: 0
                val percentage = if (target.toDouble() == 0.0) {
                    0.0
                } else {
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

    fun deleteSaving(saving: SavingTable) {
        viewModelScope.launch {
            repository.deleteSaving(saving)
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
        return !(uiState.savingCategory.isBlank() || uiState.time.isBlank() ||
                uiState.goldPrice.isBlank() || uiState.goldQuantity.isBlank() ||
                uiState.product.isBlank())
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
        return !(uiState.savingCategory.isBlank() || uiState.target.isBlank())
    }

}


data class TransactionUiState(
    val transactionDetails: TransactionTableDetails = TransactionTableDetails(),
    val isEntryValid: Boolean = false
)

data class TransactionTableDetails(
    val id: Int = 0,
    val time: String = Time.getTime() ?: "",
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

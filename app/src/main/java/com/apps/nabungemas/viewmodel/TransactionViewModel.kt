package com.apps.nabungemas.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionDao
import com.apps.nabungemas.data.TransactionTable
import kotlinx.coroutines.launch

class TransactionViewModel(private val transtionDao: TransactionDao) : ViewModel() {

    val allTransaction: LiveData<List<TransactionTable>> =
        transtionDao.getTransactions().asLiveData()

    //    val savingnikah:LiveData<Long> = transtionDao.getSaving("nikah")
    val allSaving: LiveData<List<SavingTable>> = transtionDao.getAllSaving().asLiveData()
    private var _saving = MutableLiveData<Long>()
//    val data = transtionDao.findCategorySaving("Tabungan Menikah").asLiveData()

    private var _category = MutableLiveData<Boolean>()
    val category: LiveData<Boolean> = _category

    private fun insertTransaction(transaction: TransactionTable) {
        viewModelScope.launch {
            transtionDao.insertData(transaction)
        }
    }

    fun deleteTransacation() {
        viewModelScope.launch { }
    }

    private fun insertSaving(saving: SavingTable) {
        viewModelScope.launch {
            transtionDao.insertSaving(saving)
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
            transtionDao.updateSaving(saving)
        }
    }


    private fun getNewTransaction(
        catagory: String,
        date: String,
        price: String,
        quantity: String,
        product: String
    ): TransactionTable {
        return TransactionTable(
            savingCategory = catagory,
            time = date,
            goldPrice = price.toLong(),
            goldQuantity = quantity.toDouble(),
            product = product
        )
    }

    fun addNewTransaction(
        catagory: String,
        date: String,
        price: String,
        quantity: String,
        product: String
    ) {
        val newTransaction = getNewTransaction(catagory, date, price, quantity, product)
        insertTransaction(newTransaction)
        viewModelScope.launch {
            try {
                val dataCategory = transtionDao.findCategorySaving(catagory)
                if (dataCategory != null) {
                    addNewSaving(catagory, dataCategory.target.toString())
                }
            } catch (e: Exception) {

            }

        }

    }

    fun isEntryValid(
        catagory: String,
        date: String,
        price: String,
        quantity: String,
        product: String
    ): Boolean {
        if (catagory.isBlank() || date.isBlank() || price.isBlank() || quantity.isBlank() || product.isBlank()) {
            return false
        }
        return true
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
        catagory: String,
        target: String
    ) {
        var totalSaving: Long?
        var percentage: Double
        var dataCategory: SavingTable?
        viewModelScope.launch {
            try {
                dataCategory = transtionDao.findCategorySaving(catagory)
                Log.d("date category", dataCategory.toString())

                if (dataCategory != null) {
                    val find = transtionDao.findSaving(catagory)
                    Log.d("find saving", find.toString())

                    if (find != null) {
                        totalSaving = transtionDao.getSaving(catagory)
                        Log.d("transaction", totalSaving!!.toString())

                        if (totalSaving != null) {
                            percentage =
                                (totalSaving!!.toDouble().div(target.toDouble()).times(100))
                            Log.d("percentage", percentage.toString())

                            if (dataCategory!!.savingCategory == catagory) {
                                val updatedItem =
                                    getUpdatedSaving(
                                        dataCategory!!.id,
                                        catagory,
                                        target,
                                        totalSaving!!,
                                        percentage
                                    )
                                Log.d("update", updatedItem.toString())
                                updateSaving(updatedItem)
                                Log.d("update", "update saving")
                            } else {
                                val newSaving =
                                    getNewSaving(catagory, target, totalSaving!!, percentage)
                                insertSaving(newSaving)
                                Log.d("update", "insert saving")
                            }
                        } else {
                            if (dataCategory!!.savingCategory == catagory) {
                                val updatedItem =
                                    getUpdatedSaving(
                                        dataCategory!!.id,
                                        catagory,
                                        target,
                                        0,
                                        0.0
                                    )
                                Log.d("update", updatedItem.toString())
                                updateSaving(updatedItem)
                                Log.d("update", "update saving")
                            } else {
                                val newSaving =
                                    getNewSaving(catagory, target, 0, 0.0)
                                insertSaving(newSaving)
                                Log.d("update", "insert saving")
                            }
                        }

                    } else {
                        if (dataCategory!!.savingCategory == catagory) {
                            val updatedItem =
                                getUpdatedSaving(
                                    dataCategory!!.id,
                                    catagory,
                                    target,
                                    0,
                                    0.0
                                )
                            Log.d("update", updatedItem.toString())
                            updateSaving(updatedItem)
                            Log.d("update", "update saving")
                        } else {
                            val newSaving =
                                getNewSaving(catagory, target, 0, 0.0)
                            insertSaving(newSaving)
                            Log.d("update", "insert saving")
                        }
                    }

                } else {
                    val newSaving = getNewSaving(catagory, target, 0, 0.0)
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

}
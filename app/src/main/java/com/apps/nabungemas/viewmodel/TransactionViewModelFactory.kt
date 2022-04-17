package com.apps.nabungemas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.nabungemas.data.TransactionDao

class TransactionViewModelFactory(private val transtionDao: TransactionDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TransactionViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(transtionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.apps.nabungemas.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apps.nabungemas.data.TransactionDao

//class GoldViewModelFactory(private val transtionDao: TransactionDao,private val application: Application):ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(GoldViewModel::class.java)){
//            @Suppress("UNCHECKED_CAST")
//            return GoldViewModel(transtionDao,application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
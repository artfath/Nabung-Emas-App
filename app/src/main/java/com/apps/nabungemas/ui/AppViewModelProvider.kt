package com.apps.nabungemas.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.startup.Initializer
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.viewmodel.GoldViewModel
import com.apps.nabungemas.viewmodel.TransactionViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            GoldViewModel(
                repository = dataApplication().container.transactionsRepository,
                goldRepository = dataApplication().container.goldRepository,
                application = dataApplication()
            )
        }
        initializer {
            TransactionViewModel(repository = dataApplication().container.transactionsRepository)
        }
    }
}

fun CreationExtras.dataApplication(): DataApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DataApplication)
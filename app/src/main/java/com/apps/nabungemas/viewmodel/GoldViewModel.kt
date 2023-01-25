package com.apps.nabungemas.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.work.*
import com.apps.nabungemas.data.GoldCurrencyTable
import com.apps.nabungemas.model.Currencies
import com.apps.nabungemas.model.GoldPrice
import com.apps.nabungemas.network.ApiStatus
import com.apps.nabungemas.network.GoldApi
import com.apps.nabungemas.repository.TransactionsRepository
import com.apps.nabungemas.worker.InternetWorker
import com.apps.nabungemas.worker.WorkerConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class GoldViewModel(
//    private val transactionDao: TransactionDao,
    private val repository: TransactionsRepository,
    application: Application
) : ViewModel() {

    private val workerManager = WorkManager.getInstance(application)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _currency = MutableLiveData<Currencies>()
    val currency: LiveData<Currencies> = _currency

    private val _gold = MutableLiveData<GoldPrice>()
    val gold: LiveData<GoldPrice> = _gold

    private val _percentage = MutableLiveData<Double>()
    val percentage: LiveData<Double> = _percentage

    val allTotalSaving: LiveData<Long?> =
        repository.getTotalSaving().asLiveData()
    val allTotalTarget: LiveData<Long?> =
        repository.getTotalTarget().asLiveData()
    val goldCurrency: LiveData<GoldCurrencyTable?> =
        repository.getGoldCurrency().asLiveData()
    private val _percentState = MutableStateFlow("")
    val percentState: StateFlow<String> = _percentState.asStateFlow()

    val allTargetState: Flow<Long?> = repository.getTotalTarget()
    val allSavingState: Flow<Long?> = repository.getTotalSaving()

    val goldCurrencyState: Flow<GoldCurrencyTable?> = repository.getGoldCurrency()

    val percentageState: Flow<Double?> = repository.getPercentage()

    init {
        getGoldandCurrency()
//        getGoldPrice()
//        getCurrency()
    }

    private fun getGoldCurrency() {
        viewModelScope.launch {
            try {
                val result = repository.findGoldCurrency()
                Log.d("findGold", result.toString())
                if(result == null){
//                    getCurrency()
                    getGoldPrice()
//                    updateGodCurrency()
                }
            }catch (e:Exception){
//                updateGodCurrency()
            }

        }

    }
    private fun insertGoldCurrency(item:GoldCurrencyTable){
        viewModelScope.launch {
            repository.insertGoldCurrency(item)
        }
    }

//    private fun updateGodCurrency() {
//        val currency = _currency.value?.exchangeRates?.idr
//        Log.d("currency", currency.toString())
//        val prevPrice = _gold.value?.prevPrice
//        val prevDifferent = _gold.value?.priceDifferent
//        val price = _gold.value?.priceGram24k
//        val data = getNewGold(currency!!,prevPrice!!,prevDifferent!!,price!!)
//        Log.d("data insert", data.toString())
//        insertGoldCurrency(data)
//    }

    internal fun getGoldandCurrency() {
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = PeriodicWorkRequestBuilder<InternetWorker>(24, TimeUnit.HOURS)
            .setConstraints(constraint)
            .build()

        workerManager.enqueueUniquePeriodicWork(
                WorkerConstant.NOTIFICATION_CHANNEL_NAME,
                ExistingPeriodicWorkPolicy.REPLACE,
                work
            )
    }


//    private fun getCurrency() {
//        viewModelScope.launch {
//            try {
//                val result = GoldApi.retrofitServiceCurrency
//                    .getCurrency("ed62509897ee6ff3f432b511e61fca34", "USD", "IDR")
//                _currency.value = result
//                _status.value = ApiStatus.SUCCESS
//                Log.d("data currency", result.toString())
//            } catch (e: Exception) {
//                Log.d("data currency", "error")
//                _status.value = ApiStatus.ERROR
//            }
//        }
//    }

    fun getGoldPrice() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val result = GoldApi.retrofitService.getPrice("XAU", "goldapi-1c4t5418l1ygu500-io")
                _gold.value = result
                _status.value = ApiStatus.SUCCESS
                Log.d("data", result.toString())
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

//    fun convertPrice(): Double? {
//        val idr = _currency.value?.exchangeRates?.idr
//        val priceGram = _gold.value?.priceGram24k
//        return priceGram?.times(idr!!)
//    }

    fun getPercentage(
        savingTotal: Double?,
        targetTotal: Double?
    ) {
//         val savingTotal : LiveData<Long>
//        val targetTotal :LiveData<Long>
        var result: Double?
        viewModelScope.launch {
            try {
//            savingTotal = transtionDao.getTotalSaving().asLiveData()
//            targetTotal = transtionDao.getTotalTarget().asLiveData()
//            val target =targetTotal.value
//            val saving = savingTotal.value
                if (savingTotal == 0.0 || targetTotal == 0.0) {
//                    _percentage.value = 0.0
                    _percentState.value = "0 %"
                    Log.d("data", _percentage.value.toString())
                } else {
//                    _percentage.value = savingTotal?.div(targetTotal!!)?.times(100)
                     val doublePercent = savingTotal?.div(targetTotal!!)?.times(100)!!
                    _percentState.value = String.format("%.2f %", doublePercent)
                }

            } catch (e: Exception) {
//                _percentage.value = 0.0
                _percentState.value = "0 %"
            }
        }
    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
////                val transactionDao = (this[APPLICATION_KEY] as DataApplication).database.transactionDao()
//                val application = this[APPLICATION_KEY] as DataApplication
//                GoldViewModel(
//                    repository = DataApplication().container.transactionsRepository,
//                    application = application
//                )
//            }
//        }
//    }
}




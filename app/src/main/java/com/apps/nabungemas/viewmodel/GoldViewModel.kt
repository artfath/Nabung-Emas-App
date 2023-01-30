package com.apps.nabungemas.viewmodel

import android.app.Application
import android.content.ClipData
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import androidx.work.*
import com.apps.nabungemas.data.GoldCurrencyTable
import com.apps.nabungemas.data.TransactionDao
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.model.Currencies
import com.apps.nabungemas.model.GoldPrice
import com.apps.nabungemas.model.GoldUpdatePrice
import com.apps.nabungemas.network.ApiStatus
import com.apps.nabungemas.network.GoldApi
import com.apps.nabungemas.repository.GoldRepository
import com.apps.nabungemas.repository.TransactionsRepository
import com.apps.nabungemas.worker.InternetWorker
import com.apps.nabungemas.worker.WorkerConstant
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class GoldViewModel(
    private val goldRepository: GoldRepository,
    private val repository: TransactionsRepository
) : ViewModel() {

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

    var goldUiState: GoldUiState by mutableStateOf(GoldUiState.Loading)
        private set

    val golPriceState: Flow<List<GoldCurrencyTable>> = repository.getGoldWeek()

    private val _itemGoldPrice = mutableStateListOf<Double>(0.0,0.0,0.0,0.0,0.0,0.0,0.0)
    val itemGoldPrice: List<Double> = _itemGoldPrice
    private val _itemGoldDate = mutableStateListOf<String>("","","","","","","")
    val itemGoldDate: List<String> = _itemGoldDate

    init {
        getGoldandCurrency()
        getGoldList()
        listdata()
//        getCurrency()
    }

    fun listdata() {
        viewModelScope.launch {
            repository.getGoldWeek().collect { list ->
                if(list.size == 1){
                    _itemGoldPrice.clear()
                    _itemGoldDate.clear()
                    _itemGoldPrice.add(0.0)
                    _itemGoldDate.add("")
                    list.forEachIndexed { index, item ->
                        _itemGoldPrice.add(item.priceGram24k!!)
                        _itemGoldDate.add(item.dateGold?.substring(0, 5).toString())
                    }
                }else{
                    _itemGoldPrice.clear()
                    _itemGoldDate.clear()
                    list.forEachIndexed { index, item ->
                        _itemGoldPrice.add(index, item.priceGram24k!!)
                        _itemGoldDate.add(index, item.dateGold?.substring(0, 5).toString())
                    }
                }



                Log.e("graphlist", list.toString())


            }
        }
    }

    private fun getGoldCurrency() {
        viewModelScope.launch {
            try {
                val result = repository.findGoldCurrency()
                Log.d("findGold", result.toString())
                if (result == null) {
//                    getCurrency()
                    getGoldPrice()
//                    updateGodCurrency()
                }
            } catch (e: Exception) {
//                updateGodCurrency()
            }

        }

    }

    private fun insertGoldCurrency(item: GoldCurrencyTable) {
        viewModelScope.launch {
            repository.insertGoldCurrency(item)
        }
    }


    internal fun getGoldandCurrency() {
        repository.getGoldCurrencyOnline()
//        val constraint = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//
//        val work = PeriodicWorkRequestBuilder<InternetWorker>(24, TimeUnit.HOURS)
//            .setConstraints(constraint)
//            .build()
//
//        workerManager.enqueueUniquePeriodicWork(
//                WorkerConstant.NOTIFICATION_CHANNEL_NAME,
//                ExistingPeriodicWorkPolicy.REPLACE,
//                work
//            )
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

    fun getGoldList() {
        viewModelScope.launch {
            goldUiState = GoldUiState.Loading
            try {
                val result = goldRepository.getPrice()
                goldUiState = GoldUiState.Success(result)
            } catch (e: Exception) {
                goldUiState = GoldUiState.Error
            }
        }
    }


}

sealed interface GoldUiState {
    data class Success(val goldData: GoldUpdatePrice.GoldResponse) : GoldUiState
    object Error : GoldUiState
    object Loading : GoldUiState
}




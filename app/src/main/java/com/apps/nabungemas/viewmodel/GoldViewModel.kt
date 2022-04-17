package com.apps.nabungemas.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.ColumnInfo
import androidx.work.ExistingWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.apps.nabungemas.data.GoldCurrenncyTable
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionDao
import com.apps.nabungemas.model.Currencies
import com.apps.nabungemas.model.GoldPrice
import com.apps.nabungemas.network.ApiStatus
import com.apps.nabungemas.network.CurrencyApi
import com.apps.nabungemas.network.GoldApi
import com.apps.nabungemas.worker.InternetWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class GoldViewModel(
    private val transtionDao: TransactionDao,
    private val application: Application
) : ViewModel() {
    private val workManager = WorkManager.getInstance(application)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _currency = MutableLiveData<Currencies>()
    val currency: LiveData<Currencies> = _currency

    private val _gold = MutableLiveData<GoldPrice>()
    val gold: LiveData<GoldPrice> = _gold

    private val _percentage = MutableLiveData<Double>()
    val percentage: LiveData<Double> = _percentage

    val allTotalSaving: LiveData<Long?> =
        transtionDao.getTotalSaving().asLiveData()
    val allTotalTarget: LiveData<Long?> =
        transtionDao.getTotalTarget().asLiveData()

    init {
        getGoldCurrency()
//        getGoldPrice()
//        getCurrency()
    }
    private fun getNewGold(
        currency: Double,
        prevPrice: Double,
        priceDifferent: Double,
        priceGram24k: Double
    ): GoldCurrenncyTable {
        return GoldCurrenncyTable(
            currency = currency,
            prevPrice = prevPrice,
            priceDifferent = priceDifferent,
            priceGram24k = priceGram24k,
        )
    }

    private fun getGoldCurrency() {
        viewModelScope.launch {
            try {
                val result = transtionDao.findGoldCurrency()
                if(result == null){
                    getCurrency()
                    getGoldPrice()
                    updateGodCurrency()
                }
            }catch (e:Exception){
                updateGodCurrency()
            }

        }

    }
    private fun insertGoldCurrency(item:GoldCurrenncyTable){
        viewModelScope.launch {
            transtionDao.insertGoldCurrency(item)
        }
    }

    private fun updateGodCurrency() {
        val currency = _currency.value?.exchangeRates?.idr
        val prevPrice = _gold.value?.prevPrice?.times(currency!!)
        val prevDifferent = _gold.value?.priceDifferent?.times(currency!!)
        val price = _gold.value?.priceGram24k?.times(currency!!)
        val data = getNewGold(currency!!,prevPrice!!,prevDifferent!!,price!!)
        insertGoldCurrency(data)
    }

    internal fun getGoldandCurrency() {
//        var continuation = workManager
//            .beginUniqueWork(
//                REQUEST_DATA,
//                ExistingWorkPolicy.REPLACE,
//                PeriodicWorkRequest
//            )
//
//        val work = PeriodicWorkRequestBuilder<InternetWorker>(1, TimeUnit.DAYS)
//            .build()


    }


    private fun getCurrency() {
        viewModelScope.launch {
            try {
                val result = CurrencyApi
                    .retrofitService
                    .getCurrency("a71c14c54b464c498d2b0927d81887ce", "USD", "IDR")
                _currency.value = result
                _status.value = ApiStatus.SUCCESS
                Log.d("data", result.toString())
            } catch (e: Exception) {
                Log.d("data", "error")
                _status.value = ApiStatus.ERROR
            }
        }
    }

    private fun getGoldPrice() {
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

    fun convertPrice(): Double? {
        val idr = _currency.value?.exchangeRates?.idr
        val priceGram = _gold.value?.priceGram24k
        return priceGram?.times(idr!!)
    }

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
                    _percentage.value = 0.0
                    Log.d("data", _percentage.value.toString())
                } else {

                    _percentage.value = savingTotal?.div(targetTotal!!)?.times(100)

                }

            } catch (e: Exception) {
                _percentage.value = 0.0
            }
        }
    }
    companion object{
        const val REQUEST_DATA="Request data "
    }
}
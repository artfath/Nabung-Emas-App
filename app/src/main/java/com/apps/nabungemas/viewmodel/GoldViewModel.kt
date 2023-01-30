package com.apps.nabungemas.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.nabungemas.data.GoldCurrencyTable
import com.apps.nabungemas.model.GoldUpdatePrice
import com.apps.nabungemas.repository.GoldRepository
import com.apps.nabungemas.repository.TransactionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class GoldViewModel(
    private val goldRepository: GoldRepository,
    private val repository: TransactionsRepository
) : ViewModel() {



    val allTargetState: Flow<Long?> = repository.getTotalTarget()
    val allSavingState: Flow<Long?> = repository.getTotalSaving()

    val goldCurrencyState: Flow<GoldCurrencyTable?> = repository.getGoldCurrency()

    val percentageState: Flow<Double?> = repository.getPercentage()

    var goldUiState: GoldUiState by mutableStateOf(GoldUiState.Loading)
        private set

    private val _itemGoldPrice = mutableStateListOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0)
    val itemGoldPrice: List<Double> = _itemGoldPrice
    private val _itemGoldDate = mutableStateListOf("","","","","","","")
    val itemGoldDate: List<String> = _itemGoldDate

    init {
        getGoldandCurrency()
        getGoldList()
        listdata()
    }

    fun listdata() {
        viewModelScope.launch {
            repository.getGoldWeek().collect { list ->
                if(list.size == 1){
                    _itemGoldPrice.clear()
                    _itemGoldDate.clear()
                    _itemGoldPrice.add(0.0)
                    _itemGoldDate.add("")
                    list.forEachIndexed { _, item ->
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





    internal fun getGoldandCurrency() {
        repository.getGoldCurrencyOnline()
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




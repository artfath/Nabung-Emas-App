package com.apps.nabungemas.repository


import com.apps.nabungemas.data.GoldCurrenncyTable
import com.apps.nabungemas.data.TransactionDao
import com.apps.nabungemas.network.GoldApi

class Repository(private val goldApi: GoldApi, private val transactionDao: TransactionDao) {
//    suspend fun getCurrency(token: String?, base: String?,target: String?) =
//        goldApi.retrofitServiceCurrency.getCurrency(token,base,target)

    suspend fun getPrice(code: String?,token: String?) = goldApi.retrofitService.getPrice(code,token)

    suspend fun insertGoldCurrency(item: GoldCurrenncyTable) = transactionDao.insertGoldCurrency(item)
}
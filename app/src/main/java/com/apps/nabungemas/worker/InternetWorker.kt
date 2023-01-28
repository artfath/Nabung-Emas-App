package com.apps.nabungemas.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.data.GoldCurrencyTable
import com.apps.nabungemas.network.CurrencyApi
import com.apps.nabungemas.network.GoldApi
import com.apps.nabungemas.worker.WorkerConstant.TROY_OUNCE_GRAM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class InternetWorker(context: Context,params:WorkerParameters):CoroutineWorker(context,params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val notification = WorkerUtils()
        val database = DataApplication().database

        try {
            notification.statusNotification("Retrieve Data",applicationContext)
            val apiOne = async {GoldApi.retrofitService.getPrice("XAU", "goldapi-1c4t5418l1ygu500-io") }
            val apiTwo = async {CurrencyApi.retrofitServiceCurrency
                .getCurrency("5afbc90a5f98e5091bcbe417", "USD", "IDR") }
//            if(apiOne.isCompleted && apiTwo.isCompleted){
                apiOne.await().let {

                    val currency = apiTwo.await().conversionRate
                    val priceGram= it.priceGram24k.times(currency)
                    val prevPrice = it.prevPrice.times(currency).div(TROY_OUNCE_GRAM)
                    val priceDifferent= (it.prevPrice.minus(it.pricePerOunce))
                        .times(currency).div(TROY_OUNCE_GRAM)
                    val dateGold= getdate(it.time)
                    val dateCurrency = getdate(apiTwo.await().time)

                    val goldCurrency = GoldCurrencyTable(
                        currency = currency,
                        prevPrice = prevPrice,
                        priceGram24k = priceGram,
                        priceDifferent = priceDifferent,
                        dateGold = dateGold,
                        dateCurrency = dateCurrency
                    )
                    database.transactionDao().insertGoldCurrency(goldCurrency)
                    Log.d("gold await",goldCurrency.toString())
                }
//            }





//            Log.d("result",result.toString())
            notification.statusNotification("Success",applicationContext)

            Result.success()
        }catch (e:Exception){
            notification.statusNotification("Failure",applicationContext)
            Result.failure()
        }


    }

    private fun getdate(time:Long):String{
        val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale.getDefault())
        val date = dateFormat.format(time*1000L)
        return date
    }

}
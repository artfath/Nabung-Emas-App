package com.apps.nabungemas.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class InternetWorker(context: Context,params:WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        val appContext = applicationContext
        return try {


            Result.success()
        }catch (throwable:Throwable){
            Result.failure()
        }
    }
}
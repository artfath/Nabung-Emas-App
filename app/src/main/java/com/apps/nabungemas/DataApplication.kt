package com.apps.nabungemas

import android.app.Application
import com.apps.nabungemas.data.AppContainer
import com.apps.nabungemas.data.DefaultAppContainer
import com.apps.nabungemas.data.TransactionRoomDatabase

class DataApplication: Application() {
    val database:TransactionRoomDatabase by lazy {
        TransactionRoomDatabase.getDatabase(this)
    }
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }

}
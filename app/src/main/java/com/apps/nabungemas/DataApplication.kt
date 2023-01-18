package com.apps.nabungemas

import android.app.Application
import com.apps.nabungemas.data.TransactionRoomDatabase

class DataApplication: Application() {
    val database:TransactionRoomDatabase by lazy {
        TransactionRoomDatabase.getDatabase(this)
    }

}
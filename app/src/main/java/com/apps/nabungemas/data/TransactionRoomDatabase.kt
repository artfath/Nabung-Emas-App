package com.apps.nabungemas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TransactionTable::class, SavingTable::class,GoldCurrenncyTable::class], version = 1, exportSchema = false)
abstract class TransactionRoomDatabase:RoomDatabase() {
    abstract fun transactionDao():TransactionDao
    companion object{
        @Volatile
        private var INSTANCE:TransactionRoomDatabase?=null

        fun getDatabase(context: Context):TransactionRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionRoomDatabase::class.java,
                    "transaction_database"
                )

                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                return instance
            }
        }
    }
}
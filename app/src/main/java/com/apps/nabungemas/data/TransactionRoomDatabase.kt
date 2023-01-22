package com.apps.nabungemas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TransactionTable::class, SavingTable::class, GoldCurrencyTable::class],
    version = 1,
    exportSchema = false
)
abstract class TransactionRoomDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: TransactionRoomDatabase? = null

        fun getDatabase(context: Context): TransactionRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TransactionRoomDatabase::class.java,
                    "transaction_database"
                )

                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }

//                INSTANCE = instance
//
//                return instance
            }
        }
    }
}
package com.apps.nabungemas.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = "time")
    val time:String="",
    @ColumnInfo(name = "category")
    val savingCategory:String="",
    @ColumnInfo(name = "price")
    val goldPrice:Long=0,
    @ColumnInfo(name = "gold_quantity")
    val goldQuantity:Double=0.0,
    @ColumnInfo(name = "product")
    val product:String=""

)

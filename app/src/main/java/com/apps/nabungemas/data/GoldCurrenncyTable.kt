package com.apps.nabungemas.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gold_currency")
data class GoldCurrenncyTable(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name = "currency")
    val currency: Double,
    @ColumnInfo(name = "prev_price")
    val prevPrice: Double,
    @ColumnInfo(name = "price_difference")
    val priceDifferent: Double,
    @ColumnInfo(name = "price_gram")
    val priceGram24k: Double
)
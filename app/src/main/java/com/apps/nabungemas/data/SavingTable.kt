package com.apps.nabungemas.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saving")
data class SavingTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = "category")
    val savingCategory:String?,
    @ColumnInfo(name = "target")
    val target:Long?,
    @ColumnInfo(name = "total_saving")
    val totalSaving:Long?,
    @ColumnInfo(name = "percentage")
    val percentage:Double?
)
package com.aliraza.expense

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income_table")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val monthYear: String,
    val amount: String,
    val description: String,
    val date: String
)
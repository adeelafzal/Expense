package com.aliraza.expense

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val monthYear: String,
    val category: String,
    val amount: String,
    val description: String,
    val date: String
)
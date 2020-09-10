package com.aliraza.expense

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ExpenseDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addExpense(income: Expense)

        @Query("SELECT * FROM expense_table ORDER BY id ASC")
        fun getAllExpense(): LiveData<List<Expense>>

}
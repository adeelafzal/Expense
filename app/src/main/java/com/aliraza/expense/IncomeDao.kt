package com.aliraza.expense

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIncome(income: Income)

    @Query("SELECT * FROM income_table ORDER BY id ASC")
    fun getAllIncome():LiveData<List<Income>>

}
package com.aliraza.expense

import androidx.lifecycle.LiveData

class IncomeRepo(private val incomeDao:IncomeDao){
    val readAllData : LiveData<List<Income>> = incomeDao.getAllIncome()

    suspend fun addIncome(income: Income){
        incomeDao.addIncome(income)
    }

}
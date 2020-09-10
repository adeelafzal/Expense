package com.aliraza.expense

import androidx.lifecycle.LiveData

class ExpenseRepo(private val expenseDao:ExpenseDao){
    val readAllData : LiveData<List<Expense>> = expenseDao.getAllExpense()

    suspend fun addExpense(expense: Expense){
        expenseDao.addExpense(expense)
    }

}
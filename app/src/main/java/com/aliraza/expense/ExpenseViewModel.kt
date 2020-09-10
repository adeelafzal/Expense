package com.aliraza.expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application:Application):AndroidViewModel(application){
    val readAllData:LiveData<List<Expense>>
    private val repo:ExpenseRepo

    init {
        val incomeDao = AppDatabase.getDatabase(application).expenseDao()
        repo = ExpenseRepo(incomeDao)
        readAllData = repo.readAllData
    }

    fun addExpense(income: Expense){
        viewModelScope.launch(Dispatchers.IO){
            repo.addExpense(income)
        }
    }


}
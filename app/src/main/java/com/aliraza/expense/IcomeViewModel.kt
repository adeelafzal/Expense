package com.aliraza.expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IcomeViewModel(application:Application):AndroidViewModel(application){
     val readAllData:LiveData<List<Income>>
    private val repo:IncomeRepo

    init {
        val incomeDao = AppDatabase.getDatabase(application).incomeDao()
        repo = IncomeRepo(incomeDao)
        readAllData = repo.readAllData
    }

    fun addIncome(income: Income){
        viewModelScope.launch(Dispatchers.IO){
            repo.addIncome(income)
        }
    }

}
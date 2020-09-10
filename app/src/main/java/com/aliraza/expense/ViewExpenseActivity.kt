package com.aliraza.expense

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_add_expense.*
import kotlinx.android.synthetic.main.activity_view_expense.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ViewExpenseActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var mExpenseViewModel: ExpenseViewModel
    var myCat: String = ""
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.aliraza.expense.R.layout.activity_view_expense)
        val myList = ArrayList<String>()
        myList.add("Jan")
        myList.add("Feb")
        myList.add("Mar")
        myList.add("Apr")
        myList.add("May")
        myList.add("Jun")
        myList.add("Jul")
        myList.add("Aug")
        myList.add("Sep")
        myList.add("Oct")
        myList.add("Nov")
        myList.add("Dec")


        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.simple_spinner_dropdown_item,
            myList
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter


        val calendar = Calendar.getInstance()
        calendar[year, month] = day
        val format =
            SimpleDateFormat("MMM")
        val dateString = format.format(calendar.time)




try{

    val getindex =   myList.indexOf(dateString)
    spinner.setSelection(getindex)

    spinner.onItemSelectedListener = this

    mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java);
    val linearLayoutManager =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    expensRecyclerView.layoutManager = linearLayoutManager



    mExpenseViewModel.readAllData.observe(this, Observer { user ->
        val myList = ArrayList<Expense>()
        for (name in user) {


            if (name.monthYear.contains(dateString)){
                myList.add(name)
            }


        }
        expensRecyclerView.adapter = ExpensAdapater(myList)

    })
}
catch (e:Exception){

}




    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        myCat = text
        mExpenseViewModel.readAllData.observe(this, Observer { user ->
            val myList = ArrayList<Expense>()
            for (name in user) {


                if (name.monthYear.contains(myCat)){
                    myList.add(name)
                }


            }
            expensRecyclerView.adapter = ExpensAdapater(myList)

        })
    }

}
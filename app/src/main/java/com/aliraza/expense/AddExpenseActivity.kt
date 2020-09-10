package com.aliraza.expense

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_expense.*
import kotlinx.android.synthetic.main.activity_add_income.*
import kotlinx.android.synthetic.main.activity_add_income.idate
import kotlinx.android.synthetic.main.activity_add_income.myBtn
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    private lateinit var mExpenseViewModel: ExpenseViewModel

    var myCat:String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java);
        dmy.text = SimpleDateFormat("MMM").format(c.getTime()) + "-" + year.toString()
        vdate.text = day.toString() + "-" + month.toString() + "-" + year.toString()

        myBtn.setOnClickListener {
            val dpd = DatePickerDialog(
                this@AddExpenseActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    val calendar = Calendar.getInstance()
                    calendar[year, monthOfYear] = dayOfMonth
                    val format =
                        SimpleDateFormat("MMM-yyyy")
                    val dateString = format.format(calendar.time)
                    dmy.text = dateString

                },
                year,
                month,
                day
            )
            dpd.show()
        }



        idate.setOnClickListener {
            val dpd = DatePickerDialog(
                this@AddExpenseActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    idateText.text = "" + dayOfMonth + "-" + monthOfYear + "-" + year

                },
                year,
                month,
                day
            )
            dpd.show()
        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.cat,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        vcat.adapter = adapter
        vcat.onItemSelectedListener = this

        eSubmit.setOnClickListener {
            if(!eAmount.text.toString().trim().equals("")){
                if(eAmount.text.toString().toInt() <1 ||eAmount.text.toString().toInt()>1000000){
                    Toast.makeText(this, "Invalid Amount", Toast.LENGTH_SHORT).show()
                }else{
                    mExpenseViewModel.addExpense(Expense(
                        0,
                        dmy.text.toString(),
                        myCat,
                        eAmount.text.toString(),
                        vDesc.text.toString(),
                        vdate.text.toString()
                    ))
                    Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        myCat = text
    }

}
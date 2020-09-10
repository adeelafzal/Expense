package com.aliraza.expense

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_expense.*
import java.lang.Exception
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var mExpenseViewModel: ExpenseViewModel
    private lateinit var mIncomeViewModel: IcomeViewModel
    val rAmount: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("Profile", Context.MODE_PRIVATE)

        name.text = sharedPreferences.getString("fname", null) + " " + sharedPreferences.getString(
            "lname",
            null
        )
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        mIncomeViewModel = ViewModelProvider(this).get(IcomeViewModel::class.java)


        addButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddIncomeActivity::class.java))
        }

        expenseButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddExpenseActivity::class.java))
        }

        vExpBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, ViewExpenseActivity::class.java))
        }

    }

    override fun onResume() {
        mExpenseViewModel.readAllData.observe(this, Observer { user ->
            if (user.size > 0) {
                mExpen.text = user[user.size-1].amount
            } else {
                mExpen.text = "-"
            }

        })
        mIncomeViewModel.readAllData.observe(this, Observer { user ->
            if (user.size > 0) {
                mIncome.text = user[user.size-1].amount
                if (!mIncome.text.equals("-") && !mExpen.text.equals("-")) {
                    val incom:Int = mIncome.text.toString().toInt()
                    val exp:Int = mExpen.text.toString().toInt()
                    rAmo.text = "" + (incom - exp)
                }
            } else {
                mIncome.text = "-"
            }

        })

        super.onResume()
    }

}

package com.aliraza.expense

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_income.*
import java.text.SimpleDateFormat
import java.util.*

class AddIncomeActivity : AppCompatActivity() {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    private lateinit var mIncomeViewModel: IcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)
        mIncomeViewModel = ViewModelProvider(this).get(IcomeViewModel::class.java);


        idmy.text = SimpleDateFormat("MMM").format(c.getTime()) + "-" + year.toString()
        idateText.text = day.toString() + "-" + month.toString() + "-" + year.toString()

        myBtn.setOnClickListener {
            val dpd = DatePickerDialog(
                this@AddIncomeActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox

                    idmy.text = "" + monthOfYear + "-" + year

                },
                year,
                month,
                day
            )

            dpd.show()
        }




        idate.setOnClickListener {
            val dpd = DatePickerDialog(
                this@AddIncomeActivity,
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

        iSubmitbutton.setOnClickListener {
            if (!iamount.text.toString().trim().equals("")) {
                if (iamount.text.toString().toInt() < 1 || iamount.text.toString()
                        .toInt() > 1000000
                ) {
                    Toast.makeText(this, "Invalid Amount", Toast.LENGTH_SHORT).show()
                } else {
                    mIncomeViewModel.addIncome(Income(
                        0,
                        idmy.text.toString(),
                        iamount.text.toString(),
                        iamount.text.toString(),
                        idateText.text.toString()
                    ))
                    Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }


        }

    }

}

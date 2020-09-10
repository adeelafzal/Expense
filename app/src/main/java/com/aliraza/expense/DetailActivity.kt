package com.aliraza.expense

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dmy.text = intent.getStringExtra("monthYear")
        vcat.text = intent.getStringExtra("category")
        vAmount.text = intent.getStringExtra("amount")
        vDesc.text = intent.getStringExtra("description")
        vdate.text = intent.getStringExtra("date")

    }
}
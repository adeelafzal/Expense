package com.aliraza.expense

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                val i = Intent(this@SplashActivity, UserDataActivity::class.java)
                startActivity(i)
                finish()
            }, 3000L
        )
    }
}
package com.aliraza.expense

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_data.*

class UserDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPreferences = getSharedPreferences("Profile", Context.MODE_PRIVATE)
            if(sharedPreferences.getString("email", null)!=null){
                startActivity(Intent(this@UserDataActivity, MainActivity::class.java))
                finish()
            }


        setContentView(R.layout.activity_user_data)

        saveBtn.setOnClickListener {
            if (fName.text.toString().trim().equals("") || lName.text.toString().trim()
                    .equals("") || phone.text.toString().trim().equals("") || email.text.toString()
                    .trim().equals("")
            ) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                SaveUser(
                    fName.text.toString(),
                    lName.text.toString(),
                    phone.text.toString(),
                    email.text.toString()
                )
            }

        }

    }

    fun SaveUser(fname: String, lname: String, phone: String, email: String) {
        val sharedPreferences = getSharedPreferences("Profile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("fname", fname)
        editor.putString("lname", lname)
        editor.putString("email", email)
        editor.putString("phone", phone)
        editor.apply()
        startActivity(Intent(this@UserDataActivity, MainActivity::class.java))
        finish()
    }

}
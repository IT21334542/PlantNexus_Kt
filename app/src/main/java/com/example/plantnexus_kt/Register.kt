package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Register : AppCompatActivity() {
    private lateinit var fullname:EditText
    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var contactnumber:EditText
    private lateinit var back:ImageView
    private lateinit var next:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registercustomer)

        init()
        back.setOnClickListener { startActivity(Intent(this@Register,Register_choose::class.java)) }
        next.setOnClickListener {
            if(fullname.text.toString()=="" || username.text.toString()=="" || password.text.toString()=="" || contactnumber.text.toString()==""){
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }else if(contactnumber.length()!=10) {
                runOnUiThread {
                    val alertDialog = AlertDialog.Builder(this@Register)
                        .setTitle("Check Contact Number")
                        .setMessage("Contact number must be 10 digits")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }else{
                Log.e("error", fullname.text.toString())
                Log.e("error", username.text.toString())
                startActivity(Intent(this@Register,DashBoard_Customer::class.java))
                Toast.makeText(this,"Register Successful",Toast.LENGTH_SHORT).show()
            }
            }
    }

    private fun init(){
        fullname = findViewById(R.id.fullname)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password_register)
        contactnumber = findViewById(R.id.phonenumber)
        back = findViewById(R.id.back)
        next = findViewById(R.id.next)
    }
}
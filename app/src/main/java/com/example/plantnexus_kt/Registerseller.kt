package com.example.plantnexus_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class Registerseller : AppCompatActivity() {
    private lateinit var storename:EditText
    private lateinit var address:EditText
    private lateinit var fullname:EditText
    private lateinit var contactnumber:EditText
    private lateinit var business:Spinner
    private lateinit var next:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerseller)
        init()
    }

    private fun init(){
        storename = findViewById(R.id.storename)
        address = findViewById(R.id.address)
        fullname = findViewById(R.id.fullnameseller)
        contactnumber = findViewById(R.id.phonenumber)
        business = findViewById(R.id.business)
        next = findViewById(R.id.next)
    }
}
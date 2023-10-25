package com.example.plantnexus_kt

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.coroutines.CoroutineStart
import java.io.ByteArrayOutputStream
import android.util.Base64
import android.util.Base64.DEFAULT

import kotlin.io.encoding.ExperimentalEncodingApi

class Scan : AppCompatActivity() {

    private lateinit var scanImg : ImageView
    private lateinit var btnScan : Button
    val REQUEST_IMAGE_CAPTURE =100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        scanImg = findViewById(R.id.scan)
        btnScan = findViewById(R.id.btn_scan)

        btnScan.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try {
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
            }catch (e: ActivityNotFoundException){
                Toast.makeText(this,"Error: " + e.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            scanImg.setImageBitmap(imageBitmap)
           // Convert the image to a Base64 string
            val base64Image = bitmapToBase64(imageBitmap)

            // Send base64Image to a server

        }else
        super.onActivityResult(requestCode, resultCode, data)
    }

}
@OptIn(ExperimentalEncodingApi::class)
private fun bitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray,DEFAULT)
}
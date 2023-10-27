package com.example.plantnexus_kt

import android.app.PendingIntent.getActivity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class Login : AppCompatActivity() {

    private lateinit var username :EditText
    private lateinit var password :EditText
    private lateinit var login : Button
    private lateinit var signup : Button
    private lateinit var signin : SignInButton
    var RC_SIGN_IN : Int = 123
    private lateinit var personName :String
    private lateinit var email :String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()

        /*
        Google SignIn
         */
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signin.visibility= View.VISIBLE

        signin.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
             personName = acct.displayName.toString()
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl
            email=personEmail.toString()
            Log.e("name",personName)
        }

        /*


         */
        login.setOnClickListener {
            val uname = username.text.toString()
            val pass = password.text.toString()
            if (uname == "test" && pass == "test") {
                Log.v("pass","Login Success")
                startActivity(Intent(this@Login, DashBoard_Customer::class.java))
                Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show()
            }
        }

        signup.setOnClickListener{
            startActivity(Intent(this@Login,Register_choose::class.java))
        }


    }


    private fun init(){
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        signup = findViewById(R.id.signup)
        signin = findViewById(R.id.sign_in_button)
    }



    /*
    ------------------------
     */
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.e("name","checkout")
            Log.e("Success","Login Successfully")
            Toast.makeText(this,"$personName Login Successful",Toast.LENGTH_SHORT).show()

            runOnUiThread {
                val alertDialog = AlertDialog.Builder(this@Login)
                    .setTitle(personName)
                    .setMessage("Email: $email")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        startActivity(Intent(this@Login, DashBoard_Customer::class.java))
                    }
                    .create()
                alertDialog.show()
            }

            // Signed in successfully, show authenticated UI.

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("Error","Check api")
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)

        }
    }
}
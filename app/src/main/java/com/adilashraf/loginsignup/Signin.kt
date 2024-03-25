package com.adilashraf.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signin : AppCompatActivity() {

    private lateinit var signupTxt: TextView
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signin: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        signupTxt = findViewById(R.id.signupTxt)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signin = findViewById(R.id.signinBtn)
        auth = Firebase.auth

        signin.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            if (email != "" && password !="" ){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User is Sign in Successfully!!", Toast.LENGTH_LONG ).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else
                            Toast.makeText(this, "Error Signing In user", Toast.LENGTH_LONG ).show()
                    }
            }else{
                Toast.makeText(this, "Please Enter Details Correctly", Toast.LENGTH_LONG).show()
            }
        }

        signupTxt.setOnClickListener{
            val intent = Intent(this@Signin, Signup::class.java)
            startActivity(intent)
        }
    }
}
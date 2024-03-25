package com.adilashraf.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {

    private lateinit var loginTxt: TextView
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signup: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = Firebase.auth
        loginTxt = findViewById(R.id.loginTxt)
        name = findViewById(R.id.name)
        email = findViewById(R.id.emails)
        password = findViewById(R.id.password)
        signup = findViewById(R.id.signupBtn)

        loginTxt.setOnClickListener {
            val intent = Intent(this@Signup, Signin::class.java)
            startActivity(intent)
        }


        signup.setOnClickListener {
            val name = name.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()
            Log.d("Email", "Email --> $email and Password --> $password ")
            if (name == "") {
                Toast.makeText(this, "Please Enter Name Correctly", Toast.LENGTH_LONG).show()
            }else if (!isEmailValid(email)) {
                Toast.makeText(this, "Please Enter Email Correctly", Toast.LENGTH_LONG).show()
            } else if (!isPasswordValid(password)) {
                Toast.makeText(
                    this,
                    "Please Enter Password 1 SpecialChar,1 Digit,1 LowerCase,1 UpperCase",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                auth.createUserWithEmailAndPassword(email , password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,"User is Created Successfully!!",Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else
                            Toast.makeText(this, "Error creating user!!", Toast.LENGTH_LONG).show()

                    }


            }
        }

    }

    // Password Validation
    private fun isPasswordValid(password: String): Boolean {
        // Define your password criteria
        val minLength = 8
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { it.isLetterOrDigit().not() }

        return password.length >= minLength
                && hasUpperCase
                && hasLowerCase
                && hasDigit
                && hasSpecialChar
    }

    // Email Validation
    private fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

}
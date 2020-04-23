package com.example.instalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup_screen.*

class SignupScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var database = FirebaseDatabase.getInstance()
    private var myRef=database.getReference()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        nextbuttonform1.setOnClickListener {
            if (email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
            {
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                var user: FirebaseUser? = auth.currentUser
                                user?.sendEmailVerification()
                                        ?.addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(baseContext, "A verification link has been sent to your email ", Toast.LENGTH_LONG).show()
                                                var currentUser: FirebaseUser? = auth.currentUser
                                                var intent= Intent(this, LoginScreen::class.java)
                                                if (currentUser != null) {
                                                    intent.putExtra("email",currentUser.email)
                                                    intent.putExtra("uid",currentUser.uid)
                                                }

                                                startActivity(intent)
                                            }
                                        }
                            } else {

                                Toast.makeText(
                                        baseContext,
                                        "You Are an Existing User Please Login",
                                        Toast.LENGTH_LONG
                                ).show()

                            }

                            // ...
                        }
            }else{

                Toast.makeText(baseContext,"Please fill in all paramerters", Toast.LENGTH_LONG).show()

            }


        }
    }
}
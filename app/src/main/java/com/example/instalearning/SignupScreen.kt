package com.example.instalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup_screen.*

class SignupScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


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

                                                Toast.makeText(
                                                        baseContext,
                                                        "User Creation was success",
                                                        Toast.LENGTH_LONG
                                                ).show()


                                                startActivity(Intent(this, LoginScreen::class.java))
                                                finish()

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

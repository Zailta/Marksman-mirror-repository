package com.example.instalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var database = FirebaseDatabase.getInstance()
    private var myRef=database.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        auth = FirebaseAuth.getInstance()



        loginbutton.setOnClickListener {

            if (loginemail.text.toString().isNotEmpty() && loginpassword.text.toString()
                            .isNotEmpty()
            ){

                auth.signInWithEmailAndPassword(
                        loginemail.text.toString(),
                        loginpassword.text.toString()
                )
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user: FirebaseUser? = auth.currentUser
                                //save in database
                                if (user != null) {
                                    myRef.child("Users").child(SplitString(user.email.toString()))
                                            .setValue(user.uid)
                                }
                                updateUI(user)

                            } else {
                                Toast.makeText(baseContext, "Login Failed", Toast.LENGTH_LONG).show()
                                updateUI(null)

                            }

                        }
            }else{
                Toast.makeText(baseContext,"Please enter in all parameters", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser!=null) {
            if (currentUser.isEmailVerified) {
                var intent= Intent(this, HomeScreen::class.java)
                intent.putExtra("email",currentUser.email)
                intent.putExtra("uid",currentUser.uid)
                startActivity(intent)

            }else{
                Toast.makeText(baseContext,"Please Verify Email", Toast.LENGTH_LONG).show()
            }
        }else
        {
            Toast.makeText(baseContext,"something went wrong, please try again", Toast.LENGTH_LONG).show()
        }

    }
    fun SplitString(str:String):String{
        var split = str.split("@")
        return split[0]
    }
}

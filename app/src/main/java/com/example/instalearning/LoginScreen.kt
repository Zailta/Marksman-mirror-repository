package com.example.instalearning

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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



        // imageButton for back button on login activity
        imageButton1.setOnClickListener {
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }


        //login button for loggin in user
        loginbutton.setOnClickListener {

            if (loginemail.text.toString().isNotEmpty() && loginpassword.text.toString()
                            .isNotEmpty()
            ){

                auth.signInWithEmailAndPassword(loginemail.text.toString(), loginpassword.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user: FirebaseUser? = auth.currentUser

                                //save in database
                                if (user != null && user.isEmailVerified) {
                                    myRef.child("Verified Users").child(SplitString(user.email.toString()))
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

        //Image button for forgot password option

        forgotpassword.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dialog_box_forgot_password, null)
            val username = view.findViewById<EditText>(R.id.forgotpassworddialogbox)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotpassword(username)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()

        }

    }

    //creating function for forgot button :

    private fun forgotpassword(username:EditText){
        if(username.text.toString().isEmpty()){
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return
        }
        auth.sendPasswordResetEmail(username.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                       Toast.makeText(this, "A password confirmation link has been sent on your e-mail",Toast.LENGTH_LONG).show()
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

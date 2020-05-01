package com.example.instalearning

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_resetpassword.*

class Resetpassword : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword)
        auth = FirebaseAuth.getInstance()

        changepassword.setOnClickListener {

            ChangePassword()

        }


    }

    private fun ChangePassword(){
        if(oldpassword.text.isNotEmpty() && newpassword.text.isNotEmpty() && confirmpassword.text.isNotEmpty()){

            if(newpassword.text.toString().equals(confirmpassword.text.toString())){
                var user: FirebaseUser? = auth.currentUser
                if(user!=null && user.email!= null){
                    val credential = EmailAuthProvider
                            .getCredential(user.email!!, oldpassword.text.toString())

// Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                            .addOnCompleteListener {

                                if(it.isSuccessful){
                                    Toast.makeText(baseContext,"Re-Authentication Success", Toast.LENGTH_LONG).show()
                                    user.updatePassword(newpassword.text.toString())
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Toast.makeText(this,"password Changed Successfully", Toast.LENGTH_LONG).show()
                                                    startActivity(Intent(this,Profile::class.java))
                                                }
                                            }


                                }else{
                                    Toast.makeText(baseContext,"Re-authentication Failed, please enter correct password", Toast.LENGTH_LONG).show()

                                }
                            }

                }else{
                    startActivity(Intent(this,Profile::class.java))
                    finish()
                }


            }else{
                Toast.makeText(baseContext,"Passwords do not match", Toast.LENGTH_LONG).show()

            }

        }else{
            Toast.makeText(baseContext,"Please Fill all Fields", Toast.LENGTH_LONG).show()
        }
    }
}

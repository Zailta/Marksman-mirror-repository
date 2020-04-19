package com.example.instalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mFirebase: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebase = FirebaseAuth.getInstance()
        // button intents to SignUp activity
        getstarted.setOnClickListener {
            startActivity(Intent(this, SignupScreen::class.java))
        }
        //button intents to login activity
        login.setOnClickListener {
            startActivity(Intent(this, LoginScreen::class.java))
        }
    }

    public override fun onStart() {
        super.onStart()
        var mFirebaseuser: FirebaseUser? = mFirebase?.currentUser
        if (mFirebaseuser!= null){
            startActivity(Intent(this, HomeScreen::class.java))
        }else
        {
            Toast.makeText(baseContext,"Welcome", Toast.LENGTH_LONG).show()
        }
    }
}

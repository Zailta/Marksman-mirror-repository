package com.example.instalearning

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instalearning.model.Member
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile_info.*

class ProfileInfo : AppCompatActivity() {

    private var database = FirebaseDatabase.getInstance()
    private var myRef=database.reference
    val user = FirebaseAuth.getInstance().currentUser
   var  member : Member? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)

        backarrow.setOnClickListener {
            startActivity(Intent(this , Profile::class.java))
            finish()
        }


        //Initialize and Assign Variable
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        //Set Profile Selected
        bottomNavigationView.selectedItemId = R.id.menu_profile


        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_calendar -> {
                    startActivity(Intent(applicationContext, Calendar::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_home -> {
                    startActivity(Intent(applicationContext, NewHomeScreen::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_profile -> return@OnNavigationItemSelectedListener true
                else -> {
                }
            }
            false
        })

        // code to upload user details into
        if (user != null) {
            myRef.child("Users").child(SplitString(user.getEmail().toString()) ).addValueEventListener(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    //for Name
                    var name:String = dataSnapshot.child("name").value.toString()
                    viewname1.text = name

                    var actual_name:String = dataSnapshot.child("name").value.toString()
                    viewname.text = actual_name
                    //for email
                    var email:String = dataSnapshot.child("email").value.toString()
                    viewemail.text = email
                    //for phone
                    var phone:String = dataSnapshot.child("phoneNum").value.toString()
                    viewphone.text = phone
                    // for gender
                    var gender:String = dataSnapshot.child("gender").value.toString()
                    viewgender.text = gender
                    // for prof. cat.
                    var category:String = dataSnapshot.child("category").value.toString()
                   viewcategory.text = category
                    // for profession
                    var profession:String = dataSnapshot.child("profession").value.toString()
                    viewprofession.text = profession
                    // for Domain
                    var domain:String = dataSnapshot.child("domain").value.toString()
                    viewdomain.text = domain
                    // for Inst.
                    var inst:String = dataSnapshot.child("cInstitute").value.toString()
                    viewinstitute.text = inst
                    // for hours per week
                    var hours:String = dataSnapshot.child("hour").value.toString()
                    viewhours.text = hours
                    // for fee per class
                    var fee:String = dataSnapshot.child("fees").value.toString()
                    viewfees.text = fee
                    var profile: String = dataSnapshot.child("imageID").value.toString()
                   Picasso.get().load(profile).into(profilepicture)

                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

    }


    fun SplitString(str:String):String{
        var split = str.split(".", "@")
        return split[0]
    }
}
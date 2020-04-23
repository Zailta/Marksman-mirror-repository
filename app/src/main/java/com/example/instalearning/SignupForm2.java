package com.example.instalearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.instalearning.model.Member;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupForm2 extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // Write a message to the database
    DatabaseReference rootref = db.getReference();
    DatabaseReference userRef = rootref.child("Users");



    //Edit Text for HoursPerWeek and FeesPerClass
    EditText hours,fees;
    //ImageButton for previous page(activity_signup_form3.xml) and submit button
    ImageButton buttonFive, buttonSix;

    // class that it used for grabing all details
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form2);

        //grabing all fields
        hours = findViewById(R.id.editText9);
        fees = findViewById(R.id.editText10);
        buttonFive = findViewById(R.id.form2backbutton);
        buttonSix = findViewById(R.id.nextbuttonform2);

        //getting data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        member = bundle.getParcelable("Members");

        // This button takes back to activity_signup_form3.xml
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignupForm3.class);
                startActivity(intent);
            }
        });

        // this button is submit button after clicking which all datas will go to firebase real time database
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Please login to continue", Toast.LENGTH_SHORT).show();
            }
        });

    }


    // after user clicks the submit button it details goes to Firebase database
    public void submit()

    {
            String hourValue = hours.getText().toString();
            String feesValue = fees.getText().toString();

            member.setHour(hourValue);
            member.setFees(feesValue);

        userRef.push().setValue(member).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Toast.makeText(SignupForm2.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupForm2.this, "Failure Try Again", Toast.LENGTH_SHORT).show();
                }
            })

;    }


}

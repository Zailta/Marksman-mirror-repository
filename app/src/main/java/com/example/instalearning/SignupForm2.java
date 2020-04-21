package com.example.instalearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignupForm2 extends AppCompatActivity {


    //Edit Text for HoursPerWeek and FeesPerClass
    EditText hours,fees;
    //ImageButton for previous page(activity_signup_form3.xml) and submit button
    ImageButton buttonFive, buttonSix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form2);

        //grabing all fields
        hours = findViewById(R.id.editText9);
        fees = findViewById(R.id.editText9);
        buttonFive = findViewById(R.id.form2backbutton);
        buttonSix = findViewById(R.id.nextbuttonform2);

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

            }
        });

    }
}

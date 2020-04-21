package com.example.instalearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignupForm1 extends AppCompatActivity {

    //Edit Text for Name and Phone Number
    EditText name,phoneNum;
    //ImageButton for previous page(activity_signup_screen.xml) and next page(activity_form3.xml)
    ImageButton buttonOne, buttonTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form1);

        //grabing all fields
        name =  findViewById(R.id.editText);
        phoneNum = findViewById(R.id.editText2);
        buttonOne = findViewById(R.id.form1backbutton);
        buttonTwo = findViewById(R.id.nextbuttonform1);

        //This button takes back to activity_signup_form1.xml
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupScreen.class);
                startActivity(intent);
                finish();
            }
        });

        //This button takes user to activity_signup_form3.xml
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClickOne();
            }
        });

    }


    // this function takes all the data of activity_signup_form1.xml to next activity i.e activity_signup_form3.xml
    public void nextClickOne()
    {
        // To check whether user as filled all the details in activity_signup_form1.xml or not
        if(name.getText().toString().isEmpty() && phoneNum.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Please fill all the parameters",Toast.LENGTH_SHORT).show();
        }

        // takes the user to activity_signup_form3.xml
        else
        {
            Bundle bundle = new Bundle();
            String nameValue = name.getText().toString();
            String phoneVal = phoneNum.getText().toString();
            bundle.putString("Name",nameValue);
            bundle.putString("Phone",phoneVal);
            Intent intent = new Intent(SignupForm1.this,SignupForm3.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}

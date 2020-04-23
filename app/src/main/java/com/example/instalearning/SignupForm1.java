package com.example.instalearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.instalearning.model.Member;

public class SignupForm1 extends AppCompatActivity {

    //Edit Text for Name and Phone Number
    EditText name,phoneNum;
    //ImageButton for previous page(activity_signup_screen.xml) and next page(activity_form3.xml)
    ImageButton buttonOne, buttonTwo;
    //Radiogroup for gender
    RadioGroup mGender;
    //RadioButton for gender selection
    RadioButton mGenderOption;


    // Strings for data
    String email, nameValue, phoneVal,genderVal;
    //Class that is used to grab all details
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form1);

        //grabing all fields
        name =  findViewById(R.id.editText);
        phoneNum = findViewById(R.id.editText2);
        buttonOne = findViewById(R.id.form1backbutton);
        buttonTwo = findViewById(R.id.nextbuttonform1);
        mGender = findViewById(R.id.gender);


//        grabing email from signup page
        Intent intent = getIntent();
        email = intent.getStringExtra("email");


        // functionality of radioGroup Button

        mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mGenderOption = mGender.findViewById(checkedId);

                switch (checkedId)
                {
                    case R.id.radioButton1:
                        genderVal = mGenderOption.getText().toString();
                        break;
                    case R.id.radioButton2:
                        genderVal = mGenderOption.getText().toString();
                        break;
                    case R.id.radioButton3:
                        genderVal = mGenderOption.getText().toString();
                        break;
                    case R.id.radioButton4:
                        genderVal = mGenderOption.getText().toString();
                        break;
                    default:
                }
            }
        });


        //This button takes back to activity_signup_form1.xml
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
        if(name.getText().toString().isEmpty() && phoneNum.getText().toString().isEmpty() && mGender.toString().isEmpty())
        {
            Toast.makeText(this,"Please fill all the parameters",Toast.LENGTH_SHORT).show();
        }

        // takes the user to activity_signup_form3.xml
        else
        {
             Bundle bundle = new Bundle();
             nameValue = name.getText().toString();
             phoneVal = phoneNum.getText().toString();

             member = new Member();
             member.setEmail(email);
             member.setName(nameValue);
             member.setPhoneNum(phoneVal);
             member.setGender(genderVal);

            bundle.putParcelable("Member",member);
            Intent intent = new Intent(SignupForm1.this,SignupForm3.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}

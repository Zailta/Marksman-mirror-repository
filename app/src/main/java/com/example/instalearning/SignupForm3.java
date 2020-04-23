package com.example.instalearning;

import androidx.appcompat.app.AppCompatActivity;
import com.example.instalearning.model.Member;

import android.content.Intent;
import android.os.Bundle;
import android.os.MemoryFile;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupForm3 extends AppCompatActivity {


    // class used to grab all the details
    private Member member;

    //Edit Text for profession, Domain, Current Institute
    EditText profession, domain, cInstitute;
    // Image Button for previous page(activity_signup_form1.xml) and next page(activity_signup_form2.xml)
    ImageButton buttonThree, buttonFour;
    //Radiogroup for category
    RadioGroup mCategory;
    //RadioButton for category selection
    RadioButton mCategoryOption;

    // Strings for data
    String professionValue, domainValue, cInstituteValue, categoryValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form3);

        //grabing all fields
        profession = findViewById(R.id.editText4);
        domain = findViewById(R.id.editText3);
        cInstitute = findViewById(R.id.editText5);
        buttonThree = findViewById(R.id.form3backbutton);
        buttonFour = findViewById(R.id.nextbuttonform3);
        mCategory = findViewById(R.id.category);



        //grabing details of previous page(activity_signup_form1.xml)
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        member = bundle.getParcelable("Member");


        //functionality of radioGroup Button
        mCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mCategoryOption = mCategory.findViewById(checkedId);

                switch (checkedId)
                {
                    case R.id.radioButton5:
                        categoryValue = mCategoryOption.getText().toString();
                        break;
                    case R.id.radioButton6:
                        categoryValue = mCategoryOption.getText().toString();
                        break;
                    case R.id.radioButton7:
                        categoryValue = mCategoryOption.getText().toString();
                        break;
                    default:
                }

            }
        });


        //this button takes user back to activity_signup_form1.xml
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupForm1.class);
                startActivity(intent);
            }
        });

        //this button takes user to activity_signup_form2.xml
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextClickThree();





            }
        });




    }

    public  void nextClickThree()
    {
        // To check whether user as filled all the details in activity_signup_form3.xml or not
        if(profession.getText().toString().isEmpty() && domain.getText().toString().isEmpty() && cInstitute.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Please fill all the Parameters",Toast.LENGTH_SHORT).show();
        }

//        this  grabs all details from previous activity and sends it to next
        else
        {

            Bundle bundle = new Bundle();

            professionValue = profession.getText().toString();
            domainValue = domain.getText().toString();
            cInstituteValue = cInstitute.getText().toString();


            member.setProfession(professionValue);
            member.setDomain(domainValue);
            member.setcInstitute(cInstituteValue);
            member.setCategory(categoryValue);

            bundle.putParcelable("Members",member);

            Intent intent = new Intent(SignupForm3.this,SignupForm2.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}

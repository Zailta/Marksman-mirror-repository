package com.example.instalearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.instalearning.model.CalendarInfo;
import com.example.instalearning.model.Member;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Calendar extends AppCompatActivity {
    CalendarView calendarView;
    EditText eventNameEditText;
    EditText eventDescriptionEditText;
    Button saveButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference booking = database.getReference("Users");
    FirebaseAuth auth=FirebaseAuth.getInstance();
    String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        calendarView=(CalendarView)findViewById(R.id.calendarView);
        eventNameEditText=(EditText)findViewById(R.id.eventNameEditText);
        eventDescriptionEditText=(EditText)findViewById(R.id.eventDescriptionEditText);
        saveButton=(Button)findViewById(R.id.saveButton);
        //Set Calendar Selected
        bottomNavigationView.setSelectedItemId(R.id.menu_calendar);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.menu_calendar:
                        return true;


                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(),NewHomeScreen.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.menu_profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    default:
                }
                return false;
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName=eventNameEditText.getText().toString();
                String eventDescription=eventDescriptionEditText.getText().toString();
                addDate(selectedDate,eventName,eventDescription);
            }
        });

    }

    private void addDate(String selectedDate, String eventName, String eventDescription) {
        if(!TextUtils.isEmpty(eventName)&& !TextUtils.isEmpty(eventDescription)){
            FirebaseUser user=auth.getCurrentUser();
            CalendarInfo cal=new CalendarInfo(selectedDate,eventName,eventDescription);
            booking.child(SplitString(user.getEmail().toString())).child("Schedule").push().setValue(cal);
            Toast.makeText(this,"Meeting Scheduled",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Details Incomplete",Toast.LENGTH_LONG).show();
        }
    }
    private String SplitString(String email){
        String[] For_split_email=email.split("[@._]");
        return For_split_email[0];
    }
}

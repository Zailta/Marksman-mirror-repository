package com.example.instalearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    FirebaseAuth auth;
    ImageButton account, password, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth = FirebaseAuth.getInstance();

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        //Set Profile Selected
        bottomNavigationView.setSelectedItemId(R.id.menu_profile);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.menu_calendar:
                        startActivity(new Intent(getApplicationContext(),Calendar.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(),NewHomeScreen.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.menu_profile:

                        return true;

                    default:

                }
                return false;
            }
        });
        account = findViewById(R.id.myaccButton);
        password = findViewById(R.id.passwordButton);
        logout = findViewById(R.id.logoutButton);
        // intent for account button
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfileInfo.class));
                overridePendingTransition(0,0);
            }
        });

        // intent for password button
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Resetpassword.class));
                overridePendingTransition(0,0);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(),LoginScreen.class));
                finish();
            }
        });


    }
}

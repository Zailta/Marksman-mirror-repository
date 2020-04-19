package com.example.instalearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view=this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.darkolivegreen);
        EditText emailEditText=(EditText)findViewById(R.id.emailEditText);
        EditText passwordEditText=(EditText)findViewById(R.id.passwordEditText);
        Button loginButton=(Button)findViewById(R.id.loginButton);
        ImageButton  imageButton=(ImageButton)findViewById(R.id.imageButton1);
    }
}

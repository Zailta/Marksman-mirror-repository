package com.example.instalearning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instalearning.model.Member;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class SignupForm2 extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // Write a message to the database
    DatabaseReference rootref = db.getReference("Users");

    //Firebase code for storage
    private FirebaseStorage storage;
    private StorageReference storageReference;



    //Edit Text for HoursPerWeek and FeesPerClass
    EditText hours,fees;
    //ImageButton for previous page(activity_signup_form3.xml) and submit button
    ImageButton buttonFive, buttonSix;
    //ImageView for profile Picture
     ImageView profileImage;
     //uri
    public  Uri imageUri;

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
        profileImage = findViewById(R.id.profileUploadImage);

        //initializing firebase storage
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //getting data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        member = bundle.getParcelable("Member");

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
                Intent intent = new Intent(getApplicationContext(),NewHomeScreen.class);
                startActivity(intent);

            }
        });

        //for profile image
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choosePicture();
            }
        });


    }


    // after user clicks the submit button it details goes to Firebase database
    public void submit()

    {
            String hourValue = hours.getText().toString();
            String feesValue = fees.getText().toString();
            String actualEmail;
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            member.setHour(hourValue);
            member.setFees(feesValue);

        rootref.child(SplitString(user.getEmail().toString())).setValue(member).addOnSuccessListener(new OnSuccessListener<Void>() {
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
    private String SplitString(String email){
        String[] For_split_email=email.split("[@._]");
        return For_split_email[0];
    }

    //function for choose picture
    public void choosePicture()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode == RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
            uploadPicture();
        }
    }

    //function for uploading picture

    private void uploadPicture()
    {
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(),"Failed To Upload",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

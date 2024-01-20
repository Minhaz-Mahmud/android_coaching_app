package com.example.betaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    Toolbar toolbar;

    EditText mName,mAddress,mPhone;
    Button editChangeButton;

    FirebaseAuth fAuth;

    private DatabaseReference mDatabase;

    String UserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        toolbar=findViewById(R.id.tlbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mName=findViewById(R.id.editName);
        mAddress=findViewById(R.id.editAddress);
        mPhone=findViewById(R.id.editphone);
        editChangeButton=findViewById(R.id.editChangeButton);


        fAuth= FirebaseAuth.getInstance();

        mDatabase= FirebaseDatabase.getInstance().getReference("users");

//      if(fAuth.getCurrentUser() !=null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//           finish();
//       }


        editChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName=mName.getText().toString();
                String phone=mPhone.getText().toString();
                String address=mAddress.getText().toString();

                if (TextUtils.isEmpty(fullName)) {
                    mName.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    mPhone.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(address)) {
                    mAddress.setError("Email is Required.");
                    return;
                }

                UserId =fAuth.getCurrentUser().getUid();
                mDatabase.child(UserId).child("address").setValue(address);
                mDatabase.child(UserId).child("name").setValue(fullName);
                mDatabase.child(UserId).child("phone").setValue(phone);

                Toast.makeText(EditProfile.this, "Profile Edited", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditProfile.this,Account.class));

            }
        });

    }
}
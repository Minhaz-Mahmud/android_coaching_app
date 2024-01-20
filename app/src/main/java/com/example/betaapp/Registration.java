package com.example.betaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Registration extends AppCompatActivity {

    public static final String TAG = "TAG";

    ProgressBar progressBar;
    EditText mName,mInstitution,mAddress,mPhone,mEmail,mPassword;
    Button mRegister;
    FirebaseAuth fAuth;
    Integer x;

    private DatabaseReference mDatabase;

    String UserId;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
       mName=findViewById(R.id.regName);
      // mInstitution=findViewById(R.id.institution);
       mAddress=findViewById(R.id.regAddress);
        mEmail=findViewById(R.id.regEmail);
        mPassword=findViewById(R.id.regPassword);
        mPhone=findViewById(R.id.regPhone);
        mRegister=findViewById(R.id.registration);

        progressBar=findViewById(R.id.progressBarReg);



        fAuth=FirebaseAuth.getInstance();

        mDatabase= FirebaseDatabase.getInstance().getReference("users");

//      if(fAuth.getCurrentUser() !=null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//           finish();
//       }

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                x = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error if needed
            }
        });


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String fullName=mName.getText().toString();
                String phone=mPhone.getText().toString();
               // String institution=mInstitution.getText().toString();
                String address=mAddress.getText().toString();
                x=x+2007001;
                String SlNum = String.valueOf(x);

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Email is Required.");
                    return;
                }

                if(password.length()<6){
                    mPassword.setError("Password must be greater or equal to 6 letters");
                    return;
                }

                //register the user in  fire base

               fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);

                        if(task.isSuccessful()){
                            // Toast.makeText(Registration.this,"User Created",Toast.LENGTH_SHORT).show();
                            UserId =fAuth.getCurrentUser().getUid();

                            User user=new User(fullName,address,phone,email,SlNum);

                            mDatabase.child(UserId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess: User profile is created for "+UserId);
                                    Toast.makeText(Registration.this, "REGISTRATION COMPLETED", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure"+e.toString());
                                    //  Toast.makeText(Registration.this, "FAILED "+e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });

                            startActivity(new Intent(Registration.this,MainActivity.class));
                        } else {
                            Toast.makeText(Registration.this,"Error 123 !" + task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });

    }
}
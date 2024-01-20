package com.example.betaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account extends AppCompatActivity {
    TextView profileText,changePassword,editProfile;

    Button logout;
    String UserId;

    FirebaseAuth fAuth;


    private DatabaseReference mDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        profileText=findViewById(R.id.profileText);
        changePassword=findViewById(R.id.changePassword);
        logout=findViewById(R.id.logout);
        editProfile=findViewById(R.id.editProfile);


        fAuth = FirebaseAuth.getInstance();




        FirebaseUser user = fAuth.getCurrentUser();
        if (user != null) {
            UserId = user.getUid();
        } else {
            Toast.makeText(this, "ERROR DETECTED", Toast.LENGTH_SHORT).show();

        }

        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(UserId);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    String address = dataSnapshot.child("address").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);

                    String name= dataSnapshot.child("name").getValue(String.class);
                    String phone = dataSnapshot.child("phone").getValue(String.class);
                    String roll = dataSnapshot.child("roll").getValue(String.class);


                    profileText.setText("Name: " +name+"\n"+
                            "Program Roll: " +roll+"\n"+
                            "Address: " +address+"\n"+
                            "phone: " +phone+"\n"+
                            "Email: " +email+"\n");



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error if needed
            }
        };

        // Attach the ValueEventListener to the database reference
        mDatabase.addValueEventListener(valueEventListener);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                Toast.makeText(Account.this, "LOGGED OUT", Toast.LENGTH_SHORT).show();

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Account.this,ChangePassword.class));
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Account.this,EditProfile.class));
            }
        });



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.nav_drawer_back_item)
                {
                   startActivity(new Intent(Account.this,HomePage.class));
                }


                return true;
            }
        });



    }     //onCreate ends




    public void logout() {
        FirebaseAuth.getInstance().signOut(); // logout
        startActivity(new Intent(Account.this, MainActivity.class));
        finish();
    }

}

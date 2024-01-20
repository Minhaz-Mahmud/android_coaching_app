package com.example.betaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Notifications extends AppCompatActivity {


    Toolbar toolbar;
    TextView note;
    Button clear;

    FirebaseAuth fAuth;

    private DatabaseReference mDatabase;

    String UserId;

    Integer x=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        toolbar=findViewById(R.id.tlbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        note=findViewById(R.id.note);
        clear=findViewById(R.id.clear);

        fAuth=FirebaseAuth.getInstance();
        UserId =fAuth.getCurrentUser().getUid();

        mDatabase= FirebaseDatabase.getInstance().getReference("users").child(UserId).child("z_Notifications");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                        String course = userSnapshot.getValue(String.class);

                        note.append("\n\n* You bought the course "+course+"\n"+"Your  program will start from the next month\n");
                        x++;
                    }}
                else {note.setText("No notifications to show ):  ");}

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error if needed
            }
        };
        mDatabase.addValueEventListener(valueEventListener);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               note.setText("");
            }
        });

    }
}
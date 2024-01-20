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

public class EnrolledCourses extends AppCompatActivity {


    Toolbar toolbar;
    TextView enrolledCourses;
    Button BuyMoreCourses;

    FirebaseAuth fAuth;

    private DatabaseReference mDatabase;

    String UserId;

    Integer x=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_courses);

        toolbar=findViewById(R.id.tlbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        enrolledCourses=findViewById(R.id.enrolled_courses);
        BuyMoreCourses=findViewById(R.id.buy_more_course);

        fAuth=FirebaseAuth.getInstance();
        UserId =fAuth.getCurrentUser().getUid();

        mDatabase= FirebaseDatabase.getInstance().getReference("users").child(UserId).child("z_Courses");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    String course = userSnapshot.getValue(String.class);

                    enrolledCourses.append("Course: "+x+":"+course+"\n\n");
                    x++;
                }}
                else {enrolledCourses.setText("You haven't enrolled any course  ):  ");}

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        };
        mDatabase.addValueEventListener(valueEventListener);


        BuyMoreCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnrolledCourses.this,BuyCourses.class));
            }
        });

    }
}
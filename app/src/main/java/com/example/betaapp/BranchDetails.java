package com.example.betaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BranchDetails extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_details);
        toolbar=findViewById(R.id.tlbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        t1=findViewById(R.id.houseNO);
        t2=findViewById(R.id.roadNo);
        t3=findViewById(R.id.thana);
        t4=findViewById(R.id.upZilla);
        t5=findViewById(R.id.branchMobile);

        Intent intent=getIntent();
        String houseNo=intent.getStringExtra("HouseNo");
        String roadNo=intent.getStringExtra("RoadNo");
        String thana=intent.getStringExtra("Thana");
        String upazilla=intent.getStringExtra("Upazila");
        String mobile=intent.getStringExtra("Mobile");

        //Toast.makeText(this, thana+"  123 "+roadNo, Toast.LENGTH_SHORT).show();



        t1.setText("House No: "+houseNo);
        t2.setText("Road No: "+roadNo);
        t3.setText("Thana: "+thana);
        t4.setText("Upzilla: "+upazilla);
        t5.setText("Mobile No:"+mobile);
    }
}
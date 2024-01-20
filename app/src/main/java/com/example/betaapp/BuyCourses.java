package com.example.betaapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuyCourses extends AppCompatActivity implements View.OnClickListener {
    Button ssc_bangla,ssc_model_test,ssc_academic,hsc_bangla,hsc_model_test,hsc_academic;
    Button engineering,medical,university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_courses);

        ssc_bangla=findViewById(R.id.ssc_bangla_english);
        ssc_academic=findViewById(R.id.ssc_academic);
        ssc_model_test=findViewById(R.id.ssc_model_test);
        hsc_academic=findViewById(R.id.hsc_academic);
        hsc_bangla=findViewById(R.id.hsc_bangla);
        hsc_model_test=findViewById(R.id.hsc_model);
        engineering=findViewById(R.id.engineering);
        medical=findViewById(R.id.medical);
        university=findViewById(R.id.university);

        ssc_bangla.setOnClickListener(this);
        ssc_academic.setOnClickListener(this);
        ssc_model_test.setOnClickListener(this);
        hsc_model_test.setOnClickListener(this);
        hsc_academic.setOnClickListener(this);
        hsc_bangla.setOnClickListener(this);
        engineering.setOnClickListener(this);
        university.setOnClickListener(this);
        medical.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent intent=new Intent(BuyCourses.this,Transaction.class);

        if(view.getId()==R.id.ssc_bangla_english){
            intent.putExtra("course","SSC BANGLA-ENGLISH FULL COURSE");
            startActivity(intent);}

        if(view.getId()==R.id.ssc_academic) {
            intent.putExtra("course", "SSC ACADEMIC FULL COURSE");
            startActivity(intent);
        }

        if(view.getId()==R.id.ssc_model_test) {
            intent.putExtra("course", "SSC MODEL TEST");
            startActivity(intent);
        }

        if(view.getId()==R.id.hsc_academic) {
            intent.putExtra("course", "HSC ACADEMIC FULL COURSE");
            startActivity(intent);
        }

        if(view.getId()==R.id.hsc_bangla) {
            intent.putExtra("course", "HSC BANGLA-ENGLISH COURSE");
            startActivity(intent);
        }

        if(view.getId()==R.id.hsc_model) {
            intent.putExtra("course", "HSC MODEL TEST");
            startActivity(intent);
        }

        if(view.getId()==R.id.engineering) {
            intent.putExtra("course", "ENGINEERING+BIOLOGY FULL COURSE");
            startActivity(intent);
        }

        if(view.getId()==R.id.medical) {
            intent.putExtra("course", "MEDICAL+MATH FULL COURSE");
            startActivity(intent);
        }

        if(view.getId()==R.id.university) {
            intent.putExtra("course", "UNIVERSITY+GST FULL COURSE");
            startActivity(intent);
        }



    }
}
package com.example.betaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class FeedbackPage extends AppCompatActivity {

    TextInputEditText EditText1,EditText2;
    Button sendBtn,clearBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);

        EditText1=findViewById(R.id.headline_text);
        EditText2=findViewById(R.id.details_text);
        sendBtn=findViewById(R.id.send_btn);
        clearBtn=findViewById(R.id.clear_btn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String headline = EditText1.getText().toString();
                String details = EditText2.getText().toString();
                String myMail="coachingappfeedback@gmail.com";
                String recipientEmail = "betaacademy@gmail.com";
                String subject = "Subject of your email";
                String body = "Body of your email";

                String uriText = "mailto:" + Uri.encode(myMail) +
                        "?subject=" + Uri.encode(headline) +
                        "&body=" + Uri.encode(details);
                Uri uri = Uri.parse(uriText);

                Intent emailIntent = new Intent(Intent.ACTION_VIEW, uri);
                emailIntent.setPackage("com.google.android.gm"); // Gmail's package name

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(FeedbackPage.this, "Gmail is not installed.", Toast.LENGTH_SHORT).show();
                }


            }
        });


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText1.setText("");
                EditText2.setText("");
            }
        });


    }
}
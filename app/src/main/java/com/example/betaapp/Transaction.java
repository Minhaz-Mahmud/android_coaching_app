package com.example.betaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Transaction extends AppCompatActivity {

    public static final String TAG = "TAG";
     Toolbar toolbar;
    Integer x,y;
    EditText TransactionNum;
    Button paymentButton;
    FirebaseAuth fAuth;
    String roll,CourseTitle;

    private DatabaseReference mDatabase,nDatabase,mnDatabase,rmDatabase;

    private DatabaseReference admin;

    String UserId;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        toolbar=findViewById(R.id.tlbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TransactionNum=findViewById(R.id.TransactionNum);

        paymentButton=findViewById(R.id.payment);

        fAuth=FirebaseAuth.getInstance();
        UserId =fAuth.getCurrentUser().getUid();


        nDatabase=FirebaseDatabase.getInstance().getReference("users").child(UserId).child("z_Transaction_Number");
        mDatabase= FirebaseDatabase.getInstance().getReference("users").child(UserId).child("z_Courses");
        mnDatabase= FirebaseDatabase.getInstance().getReference("users").child(UserId).child("z_Notifications");



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


        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long currentTimeMillis = System.currentTimeMillis();

                // Convert the time to a human-readable format (optional)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = sdf.format(new Date(currentTimeMillis));

                x++;
                 CourseTitle=getIntent().getExtras().getString("course");
                String notifications=CourseTitle+"  on  "+formattedDate;
                String TransactionNumber=TransactionNum.getText().toString();

                String SlNum = String.valueOf(x);

                mDatabase.child(SlNum).setValue(CourseTitle);
                nDatabase.child(SlNum).setValue(TransactionNumber);
                mnDatabase.child(SlNum).setValue(notifications);
                Toast.makeText(Transaction.this, "Course Enrolled", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Transaction.this,HomePage.class));

                addAdmin();

            }
        });







    }

    private void addAdmin() {
        rmDatabase = FirebaseDatabase.getInstance().getReference("users").child(UserId);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    roll = dataSnapshot.child("roll").getValue(String.class);
                    addToAdmin();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error if needed
            }
        };

        // Attach the ValueEventListener to the database reference
        rmDatabase.addListenerForSingleValueEvent(valueEventListener);
    }

    private void addToAdmin() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        String monthYearString = monthYearFormat.format(calendar.getTime());

        admin = FirebaseDatabase.getInstance().getReference("z_admin").child(monthYearString);

        admin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                y = (int) dataSnapshot.getChildrenCount();
                y++;

                String serialNo = String.valueOf(y);

                // Add a completion listener to detect when the data is written to the database
                admin.child(serialNo).setValue(roll + "---" + CourseTitle, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            // Data was written successfully
                            // You can perform any additional actions here if needed
                        } else {
                            Toast.makeText(Transaction.this, "ERRORRRRRRRRRRR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error if needed
            }
        });
    }

}
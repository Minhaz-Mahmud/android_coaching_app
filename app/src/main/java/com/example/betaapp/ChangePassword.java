package com.example.betaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;

public class ChangePassword extends AppCompatActivity {

    EditText newPassword,oldPassword;
    Button Enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        newPassword=findViewById(R.id.newPassword);
        oldPassword=findViewById(R.id.oldPassword);
        Enter=findViewById(R.id.ChangePasswordButton);



        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();

                if (user != null) {

                    String nPassword=newPassword.getText().toString();
                    String oPassword=oldPassword.getText().toString();
                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oPassword);

                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> reauthTask) {
                                    if (reauthTask.isSuccessful()) {
                                        user.updatePassword(nPassword)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> passwordUpdateTask) {
                                                        if (passwordUpdateTask.isSuccessful()) {
                                                            Toast.makeText(ChangePassword.this, "Password has been changed successfully", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Toast.makeText(ChangePassword.this, "Failed", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    } else {

                                        Toast.makeText(ChangePassword.this, "Failed here ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                startActivity(new Intent(ChangePassword.this,Account.class));
            }
        });


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.nav_drawer_back_item)
                {
                    startActivity(new Intent(ChangePassword.this,HomePage.class));
                }


                return true;
            }
        });



    }
}
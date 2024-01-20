package com.example.betaapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String name;
    //public String institution;
    public String address;
    public String phone;

    public String email;

    public String roll;



    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name,String address,String phone,String email,String roll) {
        this.email = email;
        this.name=name;
        //this.institution=institution;
        this.address=address;
        this.phone=phone;
        this.roll=roll;

    }

}

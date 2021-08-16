package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {


    Button homeButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeButton = findViewById(R.id.homebtn);
        mAuth = FirebaseAuth.getInstance();

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setStatusBarColor(Color.parseColor("#FFF63321"));
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}
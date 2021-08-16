package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    Button logout_btn;
    FirebaseAuth mAuth;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logout_btn = findViewById(R.id.logout_button);
        mAuth = FirebaseAuth.getInstance();

        logout_btn.setOnClickListener(view -> {
            logoutUser();
        });
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(MenuActivity.this, "Press again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    private void logoutUser() {
        mAuth.signOut();
        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
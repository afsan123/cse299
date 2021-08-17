package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextInputEditText emailField;
    TextInputEditText passField;
    FirebaseAuth mAuth;
    Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_btn);
        emailField = findViewById(R.id.editTextTextPersonName);
        passField = findViewById(R.id.editTextTextPersonName2);
        signupButton = findViewById(R.id.signup_login);
        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        loginButton.setOnClickListener(view -> {
            loginUser();
        });
    }

    private void loginUser() {
        String email = emailField.getText().toString();
        String password = passField.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailField.setError("Email can't be empty");
            emailField.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            passField.setError("Password can't be empty");
            passField.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    }
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setStatusBarColor(Color.parseColor("#FFF63321"));
    }
}
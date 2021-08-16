package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button signupButton;
    EditText emailField;
    EditText passField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        signupButton = findViewById(R.id.signup_btn);
        emailField = findViewById(R.id.editTextTextPersonName);
        passField = findViewById(R.id.editTextTextPersonName2);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(view -> {
            createUser();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setStatusBarColor(Color.parseColor("#FFF63321"));
    }

    private void createUser() {
        String email = emailField.getText().toString();
        String password = passField.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailField.setError("Email can't be empty");
            emailField.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            passField.setError("Password can't be empty");
            passField.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
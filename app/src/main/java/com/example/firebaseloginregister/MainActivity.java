package com.example.firebaseloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText emailID, passwordT;
    private Button signUpBtn, askLoginBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailID = findViewById(R.id.emailText);
        passwordT = findViewById(R.id.passwordText);

        signUpBtn = findViewById(R.id.signupBtn);
        askLoginBtn = findViewById(R.id.askLogin);

        askLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String password = passwordT.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();

                if (email.isEmpty()) {
                    emailID.setError("Please enter your email ID");
                    emailID.requestFocus();
                } else if (password.isEmpty()) {
                    passwordT.setError("Please enter password");
                    passwordT.requestFocus();
                } else if (!email.isEmpty() && !password.isEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Signup Failed!!!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Signup success!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }

                        }
                    });

                }
            }
        });


    }
}

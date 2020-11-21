package com.example.serviceprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText EmailID2 , Password2;
    Button LoginButton;
    TextView NewUserCreateAccountbutton;
    FirebaseAuth fAuth;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailID2 = findViewById(R.id.TextEmail2);
        Password2 = findViewById(R.id.TextPassword2);
        progressBar2=findViewById(R.id.progressBar2);
        fAuth=FirebaseAuth.getInstance();
        LoginButton = findViewById(R.id.Loginbutton);
        NewUserCreateAccountbutton = findViewById(R.id.Newuser);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EmailID2.getText().toString().trim();     // fetching email entered by user
                String password = Password2.getText().toString().trim();

                // Validation of Email and password

                if(TextUtils.isEmpty(email))
                {
                    EmailID2.setError("Email is required");
                    return;
                }


                if(TextUtils.isEmpty(password))
                {
                    Password2.setError("Password is required");
                    return;
                }

                if(password.length()<6)
                {
                    Password2.setError("Password must be atleast 6 characters");
                    return;
                }


                progressBar2.setVisibility(View.VISIBLE);

                // signing in

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this , "Logged in successfully" , Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext() , MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(Login.this , "error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        NewUserCreateAccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Register.class));
            }
        });

    }}

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

public class ConsumerLogin extends AppCompatActivity {

    EditText EmailID3 , Password3;
    Button LoginButton3;
    TextView NewUserCreateAccountbutton3;
    FirebaseAuth fAuth;
    ProgressBar progressBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_login);

        EmailID3 = findViewById(R.id.ConsumerTextEmail2);
        Password3 = findViewById(R.id.ConsumerTextPassword2);
        progressBar3=findViewById(R.id.ConsumerprogressBar2);
        fAuth=FirebaseAuth.getInstance();
        LoginButton3 = findViewById(R.id.ConsumerLoginbutton);
        NewUserCreateAccountbutton3 = findViewById(R.id.ConsumerNewuser);


        LoginButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EmailID3.getText().toString().trim();
                String password = Password3.getText().toString().trim();

                // Validation of Email and password

                if(TextUtils.isEmpty(email))
                {
                    EmailID3.setError("Email is required");
                    return;
                }


                if(TextUtils.isEmpty(password))
                {
                    Password3.setError("Password is required");
                    return;
                }

                if(password.length()<6)
                {
                    Password3.setError("Password must be atleast 6 characters");
                    return;
                }


                progressBar3.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ConsumerLogin.this , "Logged in successfully" , Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext() , Menupage.class));

                        }
                        else
                        {
                            Toast.makeText(ConsumerLogin.this , "error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar3.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        NewUserCreateAccountbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Register.class));
            }
        });

    }}
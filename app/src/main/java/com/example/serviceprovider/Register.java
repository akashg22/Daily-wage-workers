package com.example.serviceprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText FullName , EmailID , Password , PhoneNo;
    Button RegisterButton;
    TextView Loginhere;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    Spinner fieldspinner;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FullName = findViewById(R.id.FullName);
        EmailID = findViewById(R.id.TextEmail);
        Password = findViewById(R.id.TextPassword);
        PhoneNo = findViewById(R.id.PhoneNumber);
        RegisterButton = findViewById(R.id.Registerbutton);
        Loginhere = findViewById(R.id.AlreadyRegistered);
        fieldspinner=findViewById(R.id.fieldspinner);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //if(fAuth.getCurrentUser()!=null)
        //{
          //  startActivity(new Intent(getApplicationContext() , MainActivity.class));
            //finish();
        //}


        RegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String email = EmailID.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String fullname = FullName.getText().toString().trim();
                String phonenumber = PhoneNo.getText().toString().trim();
                String field = fieldspinner.getSelectedItem().toString().trim();

                // Validation of Email and password

                if(TextUtils.isEmpty(email))
                {
                    EmailID.setError("Email is required");
                    return;
                }


                if(TextUtils.isEmpty(password))
                {
                    Password.setError("Password is required");
                    return;
                }

                if(password.length()<6)
                {
                    Password.setError("Password must be atleast 6 characters");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                // registering the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this , "Account Created" , Toast.LENGTH_SHORT).show();
                            addUser();
                            startActivity(new Intent(getApplicationContext() , MainActivity.class));


                        }
                        else
                        {
                            Toast.makeText(Register.this , "error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });


        Loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , MainActivity.class));
            }
        });


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.fieldtype,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldspinner.setAdapter(adapter);
        fieldspinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);







    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

    }

    private void addUser(){
        String email = EmailID.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String fullname = FullName.getText().toString().trim();
        String phonenumber = PhoneNo.getText().toString().trim();
        String field = fieldspinner.getSelectedItem().toString().trim();

        String id =  databaseReference.push().getKey();
        //  User user = new User(userID,userName,userEmail,userpassword,userphone,userfield);
        User user = new User(fullname,phonenumber,field);

        databaseReference.child(id).setValue(user);
        Toast.makeText(this,"user added",Toast.LENGTH_LONG).show();
    }
}
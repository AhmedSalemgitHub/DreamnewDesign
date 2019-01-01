package com.tellyourdream.tellyourdream;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName, mEmail, mPassword, mMarital, mGender, mAge;
    private Button mRegisterButton, mBackToLogin;

    private ProgressBar mProgressBar;

    FirebaseAuth mAuth;
    FirebaseFirestore mFireStore;

    String name, email, password, marital, gender, age, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();

        mName = findViewById(R.id.register_name_editText);
        mEmail = findViewById(R.id.register_email_editText);
        mPassword = findViewById(R.id.register_password_editText);
        mMarital = findViewById(R.id.register_marital_editText);
        mGender = findViewById(R.id.register_gender_editText);
        mAge = findViewById(R.id.register_age_editText);

        mProgressBar = findViewById(R.id.progressBar);

        mBackToLogin = findViewById(R.id.beck_to_login_button);
        mRegisterButton = findViewById(R.id.button_register);

        mBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mProgressBar.setVisibility(View.VISIBLE);

                name = mName.getText().toString();
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                marital = mMarital.getText().toString();
                gender = mGender.getText().toString();
                age = mAge.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) &&
                        !TextUtils.isEmpty(marital) && !TextUtils.isEmpty(gender) && !TextUtils.isEmpty(age))
                {

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                user_id = mAuth.getCurrentUser().getUid();
                                Map<String , Object> userData = new HashMap<>();

                                userData.put("name", name);
                                userData.put("email",email);
                                userData.put("marital", marital);
                                userData.put("gender",gender);
                                userData.put("age",age);


                             mFireStore.collection("Users").document(user_id).set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void aVoid) {
                                     mProgressBar.setVisibility(View.INVISIBLE);
                                     Intent toMainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                                     startActivity(toMainIntent);
                                     SharedPreferences.Editor editor = getSharedPreferences("localData", MODE_PRIVATE).edit();
                                     editor.putString("name", name);
                                     editor.putString("email", email);
                                     editor.putString("marital", marital);
                                     editor.putString("gender", gender);
                                     editor.putString("age", age);
                                     editor.apply();
                                     finish();
                                 }
                             });

                            }else{
                                Toast.makeText(RegisterActivity.this,"Error: " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                mProgressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this, "please complete all the missing data", Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }


}

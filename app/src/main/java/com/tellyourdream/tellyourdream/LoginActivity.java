package com.tellyourdream.tellyourdream;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tellyourdream.tellyourdream.MainActivity;
import com.tellyourdream.tellyourdream.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mToRegisterationBageButton;


    private String email, password;

    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null)
        {
            Intent tomain = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(tomain);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

          mEmailField =  findViewById(R.id.login_email_editText);
          mPasswordField =  findViewById(R.id.login_password_editText);
          mLoginButton =  findViewById(R.id.button_login);
          mToRegisterationBageButton =  findViewById(R.id.button_register);

          mAuth = FirebaseAuth.getInstance();

          mToRegisterationBageButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                  startActivity(intent);
              }
          });

          mLoginButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  email = mEmailField.getText().toString();
                  password = mPasswordField.getText().toString();

                  if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                  {
                      mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful())
                              {
                                  Intent tomainintent = new Intent(LoginActivity.this,MainActivity.class);
                                  startActivity(tomainintent);
                                  finish();

                              }else{
                                  Toast.makeText(LoginActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
                  }else {
                      Toast.makeText(LoginActivity.this, "fill in the missing fields", Toast.LENGTH_SHORT).show();
                  }
              }
          });


    }

}

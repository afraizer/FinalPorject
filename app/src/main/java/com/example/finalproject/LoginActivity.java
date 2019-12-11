package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    Button signup;
    String useremail;
    String userpassword;
    LandlordOpenHelper landlordOpenHelper;
    Boolean isUsername;
    Boolean isPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        isUsername = false;
        isPassword = false;
        landlordOpenHelper = new LandlordOpenHelper(LoginActivity.this);
        landlordOpenHelper.insertLandlord(new Landlord("email", "password"));
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                EditTextFilled();
            }
        });
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useremail = email.getText().toString();
                userpassword = password.getText().toString();
                Cursor cursor = landlordOpenHelper.getSelectAllListingsCursor();
                checkDatabase(cursor);
                if(checkLandlord()){
                    Intent intent = new Intent(LoginActivity.this, LandlordActivity.class);
                    intent.putExtra("email", useremail);
                    startActivity(intent);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                isUsername = false;
                isPassword = false;
                useremail = email.getText().toString();
                userpassword = password.getText().toString();
                Cursor cursor = landlordOpenHelper.getSelectAllListingsCursor();
                checkDatabase(cursor);
                if(isUsername){
                    Toast.makeText(LoginActivity.this, "This Email already has an account", Toast.LENGTH_SHORT).show();
                }
                else{
                    landlordOpenHelper.insertLandlord(new Landlord(useremail, userpassword));
                }
            }
        });

    }

    private void checkDatabase(Cursor cursor){
        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(useremail)) {
                isUsername = true;
                if (cursor.getString(2).equals(userpassword)) {
                    isPassword = true;
                }
            }
        }
    }

    private boolean checkLandlord(){
        if(isUsername){
            if(isPassword){
                return true;
            }
            else{
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Email not in database", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void EditTextFilled(){
        if(email.getText().toString().length() > 0 && signup.getText().toString().length() > 0){
            login.setEnabled(true);
            signup.setEnabled(true);
        }
    }
}

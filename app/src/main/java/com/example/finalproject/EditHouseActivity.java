package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditHouseActivity extends AppCompatActivity {

    private String addressStr;
    private String rentStr;
    private String bedroomStr;
    private String bathroomStr;
    private String leaseStr;
    private String phoneStr;
    private String emailStr;
    private long id;

    private EditText addressText;
    private EditText rentText;
    private EditText bedroomText;
    private EditText bathroomText;
    private EditText leaseText;
    private EditText phoneText;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listing);

        Intent intent = getIntent();
        if(intent != null){
            addressStr = intent.getStringExtra("address");
            rentStr = intent.getStringExtra("rent");
            bedroomStr = intent.getStringExtra("bedrooms");
            bathroomStr = intent.getStringExtra("bathrooms");
            phoneStr = intent.getStringExtra("phoneNumber");
            emailStr = intent.getStringExtra("email");
            leaseStr = intent.getStringExtra("leaseLength");
            id = intent.getLongExtra("id",0);
        }

        addressText = findViewById(R.id.addressChangeText);
        rentText = findViewById(R.id.rentChangeText);
        bedroomText = findViewById(R.id.bedroomsChangeText);
        bathroomText = findViewById(R.id.bathroomsChangeText);
        leaseText = findViewById(R.id.leaseLengthChangeText);
        phoneText = findViewById(R.id.phoneNumberChangeText);
        emailText = findViewById(R.id.emailChangeText);

        addressText.setText(addressStr);
        rentText.setText(rentStr);
        bedroomText.setText(bedroomStr);
        bathroomText.setText(bathroomStr);
        leaseText.setText(leaseStr);
        phoneText.setText(phoneStr);
        emailText.setText(emailStr);

        Button addListing = findViewById(R.id.changeButton);
        addListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignValues();
                if(checkFilled()){
                    Intent intent = new Intent();
                    intent.putExtra("address", addressStr);
                    intent.putExtra("rent", rentStr);
                    intent.putExtra("bedrooms", bedroomStr);
                    intent.putExtra("bathrooms", bathroomStr);
                    intent.putExtra("lease", leaseStr);
                    intent.putExtra("phone", phoneStr);
                    intent.putExtra("email", emailStr);
                    intent.putExtra("id", id);
                    setResult(Activity.RESULT_OK, intent);
                    EditHouseActivity.this.finish();
                }
                else{
                    Toast.makeText(EditHouseActivity.this, "Please Enter Values for ALL Inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void assignValues(){
        addressStr = addressText.getText().toString();
        rentStr = rentText.getText().toString();
        bedroomStr = bedroomText.getText().toString();
        bathroomStr = bathroomText.getText().toString();
        leaseStr = leaseText.getText().toString();
        phoneStr = phoneText.getText().toString();
        emailStr = emailText.getText().toString();
    }

    private boolean checkFilled(){
        if(addressStr.length() > 0 && rentStr.length() > 0 &&
                bedroomStr.length() > 0 && bathroomStr.length() > 0 &&
                leaseStr.length() > 0 && phoneStr.length() > 0 &&
                emailStr.length() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}

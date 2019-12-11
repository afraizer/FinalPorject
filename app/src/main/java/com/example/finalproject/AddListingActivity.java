package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddListingActivity extends AppCompatActivity {

    private String addressStr;
    private String rentStr;
    private String bedroomStr;
    private String bathroomStr;
    private String leaseStr;
    private String phoneStr;
    private String emailStr;

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
        final ListingOpenHelper listingOpenHelper = new ListingOpenHelper(this);


        addressText = findViewById(R.id.addressEditText);
        rentText = findViewById(R.id.rentEditText);
        bedroomText = findViewById(R.id.bedroomsEditText);
        bathroomText = findViewById(R.id.bathroomsEditText);
        leaseText = findViewById(R.id.leaseLengthEditText);
        phoneText = findViewById(R.id.phoneNumberEditText);
        emailText = findViewById(R.id.emailEditText);

        Button addListing = findViewById(R.id.addButton);
        addListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignValues();
                if(checkFilled()){
                    listingOpenHelper.insertListing(new Listing(addressStr, rentStr, Integer.parseInt(bedroomStr), bathroomStr, phoneStr, emailStr, leaseStr));
                    finish();
                }
                else{
                    Toast.makeText(AddListingActivity.this, "Please Enter Values for ALL Inputs", Toast.LENGTH_SHORT).show();
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

package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HousePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_page);

        Intent intent = getIntent();
        if(intent != null){
            TextView address = (TextView) findViewById(R.id.address);
            TextView rent = (TextView) findViewById(R.id.rent);
            TextView bedrooms = (TextView) findViewById(R.id.bedrooms);
            TextView bathrooms = (TextView) findViewById(R.id.bathrooms);
            TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
            TextView email = (TextView) findViewById(R.id.email);
            TextView leaseLength = (TextView) findViewById(R.id.leaseLength);
            address.setText(intent.getStringExtra("address"));
            rent.setText("$" + intent.getStringExtra("rent"));
            bedrooms.setText(intent.getIntExtra("bedrooms", 1) + " bedrooms");
            bathrooms.setText(intent.getStringExtra("bathrooms") + " bathrooms");
            phoneNumber.setText("Landlord Phone Number" + intent.getStringExtra("phoneNumber"));
            email.setText("Landlord Email" + intent.getStringExtra("email"));
            leaseLength.setText(intent.getStringExtra("leaseLength"));
        }



    }
}

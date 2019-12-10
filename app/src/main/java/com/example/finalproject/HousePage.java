package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HousePage extends AppCompatActivity {

    private String listingAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_page);

        Intent intent = getIntent();
        if(intent != null){
            TextView address = findViewById(R.id.address);
            TextView rent = findViewById(R.id.rent);
            TextView bedrooms = findViewById(R.id.bedrooms);
            TextView bathrooms = findViewById(R.id.bathrooms);
            TextView phoneNumber = findViewById(R.id.phoneNumber);
            TextView email = findViewById(R.id.email);
            TextView leaseLength = findViewById(R.id.leaseLength);
            listingAddress = intent.getStringExtra("address");
            address.setText(listingAddress);
            rent.setText("$" + intent.getStringExtra("rent"));
            bedrooms.setText(intent.getIntExtra("bedrooms", 1) + " bedrooms");
            bathrooms.setText(intent.getStringExtra("bathrooms") + " bathrooms");
            phoneNumber.setText("Landlord Phone Number" + intent.getStringExtra("phoneNumber"));
            email.setText("Landlord Email" + intent.getStringExtra("email"));
            leaseLength.setText(intent.getStringExtra("leaseLength"));
        }

        Button locationButton = (Button) findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationIntent = new Intent(HousePage.this, MapsActivity.class);
                locationIntent.putExtra("address", listingAddress);
                startActivity(locationIntent);
            }
        });

    }
}

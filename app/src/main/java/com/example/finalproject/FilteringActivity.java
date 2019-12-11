package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FilteringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);

        final EditText bedrooms = findViewById(R.id.bedroomsFilter);
        final EditText bathrooms = findViewById(R.id.bathroomsFilter);
        final EditText lease = findViewById(R.id.leaseFilter);

        Button button = findViewById(R.id.doneButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bedroomNum = bedrooms.getText().toString();
                String bathroomNum = bathrooms.getText().toString();
                String leaseNum = lease.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("bedrooms", bedroomNum);
                intent.putExtra("bathrooms", bathroomNum);
                intent.putExtra("lease", leaseNum);
                setResult(Activity.RESULT_OK, intent);
                FilteringActivity.this.finish();
            }
        });

    }
}

package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListingOpenHelper listingOpenHelper = new ListingOpenHelper(this);
        listingOpenHelper.insertListing(new Listing("address", "rent", 1, "1", "phone", "email", "length"));


        ListView listView = findViewById(R.id.houseListView);

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                listingOpenHelper.getSelectAllListingsCursor(),
                //parallel arrays... first is names of columns to get data FROM
                new String[] {ListingOpenHelper.ADDRESS, ListingOpenHelper.BEDROOMS},
                // ids of textviews to put data in
                new int[] {android.R.id.text1, android.R.id.text2},
                0
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, HousePage.class);
                Listing listing = listingOpenHelper.getListing(id);
                intent.putExtra("address", listing.getAddress());
                intent.putExtra("rent", listing.getRent());
                intent.putExtra("bedrooms", listing.getBedrooms());
                intent.putExtra("bathrooms", listing.getBathrooms());
                intent.putExtra("phoneNumber", listing.getLandlordPhone());
                intent.putExtra("email", listing.getLandlordEmail());
                intent.putExtra("leaseLength", listing.getLengthOfLease());
                startActivity(intent);
            }
        });

        listView.setAdapter(cursorAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

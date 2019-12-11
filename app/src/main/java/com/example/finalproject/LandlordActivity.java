package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class LandlordActivity extends AppCompatActivity {

    SimpleCursorAdapter cursorAdapter;
    ListingOpenHelper listingOpenHelper;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord);
        listingOpenHelper = new ListingOpenHelper(this);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        ListView listView = findViewById(R.id.LandlordListView);

        cursorAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                listingOpenHelper.getLandlordListings(email),
                new String[] {ListingOpenHelper.ADDRESS, ListingOpenHelper.BEDROOMS},
                new int[] {android.R.id.text1, android.R.id.text2},
                0
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LandlordActivity.this, HousePage.class);
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
        menuInflater.inflate(R.menu.landlordmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addHouse:
                Intent intent = new Intent(LandlordActivity.this, AddListingActivity.class);
                startActivity(intent);
                return true;
            case R.id.manageHouses:
                // intent for managing their houses
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cursorAdapter.getCursor().close(); // closes the cursor
        cursorAdapter.changeCursor(listingOpenHelper.getLandlordListings(email)); // gets cursor to update database
    }
}

package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivityTag";
    final int REQUEST_CODE = 0;

    ListingOpenHelper listingOpenHelper;
    SimpleCursorAdapter cursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listingOpenHelper = new ListingOpenHelper(this);
        //listingOpenHelper.insertListing(new Listing("address", "rent", 1, "1", "phone", "email", "length"));
        listingOpenHelper.insertListing(new Listing("502 E Boone Ave, Spokane, WA", "$200000", 1, "1" , "509-509-509", "idk@lol.net", "2 years"));
        //listingOpenHelper.deleteAllListings();

        ListView listView = findViewById(R.id.houseListView);

        cursorAdapter = new SimpleCursorAdapter(
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
        int id = item.getItemId();
        switch(id){
            case R.id.loginMenuItem:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                return super.onOptionsItemSelected(item);
            case R.id.filterIcon:
                Intent intent1 = new Intent(MainActivity.this, FilteringActivity.class);
                startActivityForResult(intent1, REQUEST_CODE);
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            String bedroomSearch = data.getStringExtra("bedrooms");
            String bathroomSearch = data.getStringExtra("bathrooms");
            String leaseSearch = data.getStringExtra("lease");
            cursorAdapter.getCursor().close(); // closes the cursor
            if(bathroomSearch.length() > 0 && bedroomSearch.length() > 0){
                // if lease search is empty
                cursorAdapter.changeCursor(listingOpenHelper.getSearchListingBedroomsAndBathrooms(bedroomSearch, bathroomSearch));
            }
            else if(bathroomSearch.length() > 0 && leaseSearch.length() > 0){
                // if bedroom empty
                cursorAdapter.changeCursor(listingOpenHelper.getSearchListingBathroomsAndLease(bathroomSearch, leaseSearch));

            }
            else if(bedroomSearch.length() > 0 && leaseSearch.length() > 0){
                // if bathroom empty
                cursorAdapter.changeCursor(listingOpenHelper.getSearchListingBedroomsAndLease(bedroomSearch, leaseSearch));
            }
            else if(bathroomSearch.length() > 0){
                // if only bathroom searched
                cursorAdapter.changeCursor(listingOpenHelper.getSearchListingBathrooms(bathroomSearch));

            }
            else if(bedroomSearch.length() > 0){
                // if only bedroom searched
                cursorAdapter.changeCursor(listingOpenHelper.getSearchListingBedrooms(bedroomSearch));
            }
            else if(leaseSearch.length() > 0){
                // if only lease searched
                cursorAdapter.changeCursor(listingOpenHelper.getSearchListingLease(leaseSearch));
            }
            else if(bathroomSearch.length() > 0 && leaseSearch.length() > 0 && bedroomSearch.length() > 0){
                // if all put
                cursorAdapter.changeCursor(listingOpenHelper.getSearchListingBedroomsAndBathroomsAndLease(bedroomSearch, bathroomSearch, leaseSearch));
            }
            else{
                // if none put
                cursorAdapter.changeCursor(listingOpenHelper.getSelectAllListingsCursor());
            }
        }


    }
}

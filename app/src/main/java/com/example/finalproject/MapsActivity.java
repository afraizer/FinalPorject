package com.example.finalproject;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if(intent != null){
            address = intent.getStringExtra("address");
        }

        addAddressMarker();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void addAddressMarker(){
        LatLng addressLatLng = getLatLngUsingGeocoding(address);
        MarkerOptions addressMarkerOptions = new MarkerOptions();
        addressMarkerOptions.title(address);
        addressMarkerOptions.position(addressLatLng);
        mMap.addMarker(addressMarkerOptions);

        CameraUpdate addressCameraUpdate = CameraUpdateFactory.newLatLngZoom(addressLatLng, 15.0f);
        mMap.moveCamera(addressCameraUpdate);

    }


    private LatLng getLatLngUsingGeocoding(String addressStr){
        LatLng latLng = null;
        Geocoder geocoder = new Geocoder(this);
        try{
            List<Address> addressList = geocoder.getFromLocationName(addressStr, 1);
            if(addressList != null && addressList.size() > 0){
                Address addressResult = addressList.get(0);
                latLng = new LatLng(addressResult.getLatitude(), addressResult.getLongitude());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return latLng;
    }

}

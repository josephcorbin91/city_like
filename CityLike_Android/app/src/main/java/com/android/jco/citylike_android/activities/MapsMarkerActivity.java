package com.android.jco.citylike_android.activities;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.jco.citylike_android.R;
import com.android.jco.citylike_android.models.SeattleBuildingPermit;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private Location currentLocation,buildingPermitLocation;
    private ArrayList<Location> locationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_specific_building_map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        currentLocation = (Location) getIntent().getExtras().getParcelable("CurrentLocation");
        buildingPermitLocation = (Location) getIntent().getExtras().getParcelable("buildingPermitLocation");
        locationList = (ArrayList<Location>)getIntent().getExtras().getSerializable("allLocations");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng currentLatLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());

        LatLng buildingPermitLatLng = new LatLng(buildingPermitLocation.getLatitude(),buildingPermitLocation.getLongitude());
        googleMap.setBuildingsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(currentLatLng)
                .title("Your location"));
        googleMap.addMarker(new MarkerOptions().position(buildingPermitLatLng)
                .title("Building location"));
     /*  for(Location location : locationList) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude()))
                    .title(((SeattleBuildingPermit)location.getExtras().getSerializable("SeattleBuildingPermit")).getAddress()));
        }
        */
        CameraPosition cameraPosition = new CameraPosition.Builder().target(buildingPermitLatLng).zoom(16).bearing(90).tilt(30).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16f));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(buildingPermitLatLng));
    }
}

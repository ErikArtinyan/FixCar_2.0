package com.example.fixcar20;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class CustomersMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static final int ACCURACY_THRESHOLD_METERS = 20;
    private static final float ZOOM_LEVEL = 17.0f;
    private GoogleMap mMap;
    GeoQuery geoQuery;
    private Circle userCircle;
    private LocationManager locationManager;
    Marker evacuatorMarker, PickUpMarker;
    private Button customerLogoutButton;
    private Button callEvacuatorButton;
    private String customerID;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private LatLng CustomerePosition;
    private int radius = 1;
    private Boolean evacuatorFound = false;
    private Boolean  requesteType = false;
    private String evacuatorFoundID;

    private DatabaseReference CustomerDatabaseRef;
    private DatabaseReference EvacuatorsAvaiableRef;
    private DatabaseReference EvacuatorsRef;
    private DatabaseReference EvacuatorsLocationRef;
    private ValueEventListener EvacuatorLocationRefListener;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private DatabaseReference AssignedCustomerePositionRef;
    LatLng currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customers_maps);

        customerLogoutButton = findViewById(R.id.customer_logout_button);
        callEvacuatorButton = findViewById(R.id.customer_order_evacuator_button);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        CustomerDatabaseRef = FirebaseDatabase.getInstance().getReference().child("CustomersE Requests");
        EvacuatorsAvaiableRef = FirebaseDatabase.getInstance().getReference().child("Evacuator Avaiable");
     //   EvacuatorsLocationRef = FirebaseDatabase.getInstance().getReference().child("Evacuator Working");

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch mapTypeSwitch = findViewById(R.id.map_type_switch);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        customerLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                logoutCustomer();
            }
        });

        callEvacuatorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (hasLocationPermission()) {

                    if(requesteType)
                    {
                        requesteType = false;
                       // geoQuery.removeAllListeners();


                        EvacuatorsAvaiableRef.removeEventListener(EvacuatorLocationRefListener);

                        if(evacuatorFound != null)
                        {
                          //  EvacuatorsRef = FirebaseDatabase.getInstance().getReference()
                          //          .child("Users").child("Evacuators").child(evacuatorFoundID).child("CustomereRideID");
                            EvacuatorsRef = FirebaseDatabase.getInstance().getReference()
                                      .child("Users").child("Evacuators").child(customerID);
                            EvacuatorsRef.removeValue();
                            evacuatorFoundID = null;
                        }

                        evacuatorFound = false;
                        radius = 1;

                        GeoFire geoFire = new GeoFire(CustomerDatabaseRef);
                        geoFire.removeLocation(customerID);

                        if(PickUpMarker != null)
                        {
                            PickUpMarker.remove();
                        }
                        if(evacuatorMarker != null)
                        {
                            evacuatorMarker.remove();
                        }

                        callEvacuatorButton.setText("Вызвать эвакуатор");

                    }
                    else
                    {
                        requesteType = true;


                        GeoFire geoFire = new GeoFire(CustomerDatabaseRef);

                        LatLng customerPosition =currentLocation;
                        //LatLng customerPosition = new LatLng(40.839175,44.45894666666666);
                        if (customerPosition != null) {
                            mMap.addMarker(new MarkerOptions().position(customerPosition).title("Я здесь"));

                            // Установить значение CustomerePosition
                            CustomerePosition = customerPosition;

                            callEvacuatorButton.setText("Поиск эвакуатора...");
                            getNearbyEvacuators();

                         //   GeoFire geoFire = new GeoFire(CustomerDatabaseRef);
                            geoFire.setLocation(customerID, new GeoLocation(customerPosition.latitude, customerPosition.longitude));
                        } else {
                            Toast.makeText(CustomersMapsActivity.this, "Местоположение не найдено", Toast.LENGTH_SHORT).show();
                        }
                    }

                    LatLng customerPosition =currentLocation;
                    //LatLng customerPosition = new LatLng(40.839175,44.45894666666666);
                    if (customerPosition != null) {
                        mMap.addMarker(new MarkerOptions().position(customerPosition).title("Я здесь"));

                        // Установить значение CustomerePosition
                        CustomerePosition = customerPosition;

                        callEvacuatorButton.setText("Поиск эвакуатора...");
                        getNearbyEvacuators();

                        GeoFire geoFire = new GeoFire(CustomerDatabaseRef);
                        geoFire.setLocation(customerID, new GeoLocation(customerPosition.latitude, customerPosition.longitude));
                    } else {
                        Toast.makeText(CustomersMapsActivity.this, "Местоположение не найдено", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    requestLocationPermission();
                }
            }
        });

        mapTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (hasLocationPermission()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
        } else {
            requestLocationPermission();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
         currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
        if (location.hasAccuracy() && location.getAccuracy() <= ACCURACY_THRESHOLD_METERS) {
            if (userCircle == null) {
                CircleOptions circleOptions = new CircleOptions()
                        .center(currentLocation)
                        .radius(location.getAccuracy())
                        .strokeWidth(1)
                        .strokeColor(Color.BLUE)
                        .fillColor(Color.parseColor("#500084d3"));
                userCircle = mMap.addCircle(circleOptions);
            } else {
                userCircle.remove();

            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, ZOOM_LEVEL));
            AssignedCustomerePositionRef = FirebaseDatabase.getInstance().getReference().child("CustomersE Requests")
                    .child(customerID).child("l");
            AssignedCustomerePositionRef.child("0").setValue(location.getLatitude());
            AssignedCustomerePositionRef.child("1").setValue(location.getLongitude());


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (hasLocationPermission()) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    private void startLocationUpdates() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    private boolean hasLocationPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private LatLng getLastKnownLocation() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Нет разрешения на местоположение
                return null;
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastKnownLocation != null) {
                return new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            }
        }
        return null;
    }

    private void logoutCustomer() {
        Intent welcomeIntent = new Intent(CustomersMapsActivity.this, WelcomeActivity.class);
        startActivity(welcomeIntent);
        finish();
    }

    private void getNearbyEvacuators() {
        GeoFire geoFire = new GeoFire(EvacuatorsAvaiableRef);
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(CustomerePosition.latitude, CustomerePosition.longitude), radius);
        geoQuery.removeAllListeners();
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                if(!evacuatorFound && requesteType) {
                    evacuatorFound = true;
                    evacuatorFoundID = key;
                    Toast.makeText(CustomersMapsActivity.this, evacuatorFoundID.toString(), Toast.LENGTH_SHORT).show();

                    EvacuatorsRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Evacuators");
                    HashMap evacuatorMap = new HashMap();
                    evacuatorMap.put("CustomereRideID", customerID);
                    EvacuatorsRef.child(customerID).setValue(evacuatorMap);

                    GetEvacuatorLocation();
                }
            }

            @Override
            public void onKeyExited(String key) {}

            @Override
            public void onKeyMoved(String key, GeoLocation location) {}

            @Override
            public void onGeoQueryReady() {
                if(!evacuatorFound) {
                    radius = radius + 1;
                    geoQuery.setRadius(radius);
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {}
        });
    }

    private void GetEvacuatorLocation() {

        EvacuatorLocationRefListener = EvacuatorsAvaiableRef.child(evacuatorFoundID).child("l").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists() && requesteType)
                        {
                            List<Object> evacuatorLocationMap = (List<Object>) dataSnapshot.getValue();
                            double locationLat = 0;
                            double locationLng = 0;

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.getKey().equals("0")) {
                                    Toast.makeText(getApplicationContext(), "works", Toast.LENGTH_SHORT).show();

                                    locationLat = Double.parseDouble(snapshot.getValue().toString());
                                } else if (snapshot.getKey().equals("1")) {
                                    locationLng = Double.parseDouble(snapshot.getValue().toString());
                                }
                            }

                            LatLng EvacuatorLatLng = new LatLng(locationLat, locationLng);

                            if(evacuatorMarker != null)
                            {
                                evacuatorMarker.remove();
                            }

                            Location location1 = new Location("");
                            location1.setLatitude(CustomerePosition.latitude);
                            location1.setLongitude(CustomerePosition.longitude);

                            Location location2 = new Location("");
                            location2.setLatitude(EvacuatorLatLng.latitude);
                            location2.setLongitude(EvacuatorLatLng.longitude);

                            float Distance = location1.distanceTo(location2);
                            if (Distance > 100)
                            {
                                String distanceText = "Ваш эвакуатор подезжает";
                                callEvacuatorButton.setText(distanceText);
                            }
                            else{
                                String distanceText = "Расстояние до эвакуатора: " + String.valueOf(Distance);
                                callEvacuatorButton.setText(distanceText);
                            }

                            MarkerOptions markerOptions = new MarkerOptions().position(EvacuatorLatLng).title("Ваш эвакуатор здесь");
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                            evacuatorMarker = mMap.addMarker(markerOptions);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
    }

}

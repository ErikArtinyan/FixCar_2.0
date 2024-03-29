package com.example.fixcar20;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class EvacuatorMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    static final int ACCURACY_THRESHOLD_METERS = 20;
    static final float ZOOM_LEVEL = 17.0f;

    private GoogleMap mMap;
    private Circle userCircle;
    private LocationManager locationManager;

    private Button LogoutEvacuatorButton, SettingsEvacuatorButton;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Boolean currentLogoutEvacuatorStatus;
    private DatabaseReference assignedCustomereRef, AssignedCustomerePositionRef,locationLatRef,locationLong;
    private String evacuatorID, customereID = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evacuator_maps);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        evacuatorID = mAuth.getCurrentUser().getUid();

        LogoutEvacuatorButton = (Button)findViewById(R.id.evacuator_logout_button);
        SettingsEvacuatorButton = (Button)findViewById(R.id.evacuator_settings_button);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LogoutEvacuatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLogoutEvacuatorStatus = true;
                mAuth.signOut();

                LogoutEvacuator();
                DisconnectEvacuator();
            }
        });

        getAssignedCustomereRequest();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this); // изменено
        }

        // Находим Switch по его ID
        Switch mapTypeSwitch = findViewById(R.id.map_type_switch);
        // Устанавливаем слушатель изменений для Switch
        mapTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Проверяем состояние Switch
                if (isChecked) {
                    // Если Switch включен, устанавливаем тип карты на обычный
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                } else {
                    // Если Switch выключен, устанавливаем тип карты на спутниковый
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });
    }

    private void getAssignedCustomereRequest() {
        assignedCustomereRef = FirebaseDatabase.getInstance().getReference()
                .child("Users").child("Evacuators");

        ///////////Chi haskanm tvyal avelacela te che
        ///////
        /////
        ///

        assignedCustomereRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    Log.e("Data changed111","data changed");
                    for (DataSnapshot childrenSnapshot:dataSnapshot.getChildren()){
                        customereID =  childrenSnapshot.child("CustomereRideID").getValue().toString();
                        getAssignedCustomerePosition();
                        Toast.makeText(EvacuatorMapsActivity.this, customereID, Toast.LENGTH_SHORT).show();

                    }





                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getAssignedCustomerePosition() {
        AssignedCustomerePositionRef = FirebaseDatabase.getInstance().getReference().child("CustomersE Requests")
                .child(customereID).child("l");

        AssignedCustomerePositionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Log.e("Data changed111","data changed2");
                   // List<Object> customerePositionMap = (List<Object>) dataSnapshot.getValue();

                    if (dataSnapshot.exists()) {
                        Double locationLat = dataSnapshot.child("0").getValue(Double.class);
                        Double locationLog = dataSnapshot.child("1").getValue(Double.class);

                        if (locationLat != null && locationLog != null) {
                            LatLng EvacuatorLatLng = new LatLng(locationLat, locationLog);
                            mMap.addMarker(new MarkerOptions().position(EvacuatorLatLng).title("Клиент тут"));
                        }
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this); // изменено
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationChanged(@NonNull Location location)
    {
        if(getApplicationContext() != null){
            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
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

                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference EvacuatorAvalablityRef = FirebaseDatabase.getInstance().getReference().child("Evacuator Avaiable");
                GeoFire geoFireAvailablity = new GeoFire(EvacuatorAvalablityRef);


                DatabaseReference EvacuatorWorkingRef = FirebaseDatabase.getInstance().getReference().child("Evacuator Working");
                GeoFire geoFireWorking = new GeoFire(EvacuatorWorkingRef);


                switch (customereID)
                {
                    case "":
                        geoFireWorking.removeLocation(userID);
                        geoFireAvailablity.setLocation(userID, new GeoLocation(location.getLatitude(), location.getLongitude()));
                        break;
                    default:
                        geoFireAvailablity.removeLocation(userID);
                        geoFireWorking.setLocation(userID, new GeoLocation(location.getLatitude(), location.getLongitude()));
                        break;
                }
            }
        }
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void returnToMyLocation(View view) {
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                LatLng currentLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLocation));
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!currentLogoutEvacuatorStatus){

            DisconnectEvacuator();
        }
    }

    private void DisconnectEvacuator()
    {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference EvacuatorAvalablityRef = FirebaseDatabase.getInstance().getReference().child("Evacuator Avaiable");

        GeoFire geoFire = new GeoFire(EvacuatorAvalablityRef);
        geoFire.removeLocation(userID);
    }

    private void LogoutEvacuator()
    {
        Intent welcomeIntent = new Intent(EvacuatorMapsActivity.this, WelcomeActivity.class);
        startActivity(welcomeIntent);
        finish();
    }

}
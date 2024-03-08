package com.example.fixcar20;

import static com.example.fixcar20.EvacuatorMapsActivity.ACCURACY_THRESHOLD_METERS;
import static com.example.fixcar20.EvacuatorMapsActivity.ZOOM_LEVEL;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.fixcar20.databinding.ActivityCustomersMapsBinding;

public class CustomersMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static final int ACCURACY_THRESHOLD_METERS = 20;
    private static final float ZOOM_LEVEL = 17.0f;
    private GoogleMap mMap;
    private Circle userCircle;
    private LocationManager locationManager;
    private ActivityCustomersMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomersMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Switch mapTypeSwitch = findViewById(R.id.map_type_switch);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
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

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
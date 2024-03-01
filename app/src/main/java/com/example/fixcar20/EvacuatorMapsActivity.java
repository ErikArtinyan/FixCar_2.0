package com.example.fixcar20;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.fixcar20.databinding.ActivityEvacuatorMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EvacuatorMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Handler handler;
    private Runnable runnable;

    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap myMap;
    Location lastLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    BottomNavigationView menu_map;
    private ActivityEvacuatorMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

       fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        binding = ActivityEvacuatorMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        menu_map = findViewById(R.id.menu_map);
        menu_map.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.mapNormal){

                    onOptionsItemSelected(item);
                }
                if(item.getItemId() == R.id.mapSputnik){
                    onOptionsItemSelected(item);
                }
                return true;
            }
        });
        menu_map.setSelectedItemId(R.id.mapSputnik);
    }






    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Vanadzor, Armenia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private void getLastLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    lastLocation = location;

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(EvacuatorMapsActivity.this);
                }
            }
        });
    }

@Override
public void onMapReady(GoogleMap googleMap) {
    myMap = googleMap;
    myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    handler = new Handler();
    final GoogleMap myMap = googleMap; // Финализируем переменную myMap
    final MarkerOptions markerOptions = new MarkerOptions().title("Моё местоположение"); // Создаем настройки для маркера

    myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); // Установить тип карты на сателлитарную

    runnable = new Runnable() {
        @Override
        public void run() {
            // Обновляем местоположение маркера
            LatLng newLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            markerOptions.position(newLocation);

            // Удаляем предыдущий маркер и добавляем новый с обновленными координатами
            myMap.clear();
            myMap.addMarker(markerOptions);

            // Перемещаем камеру к новым координатам
            myMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation));

            // Планируем выполнение Runnable через 30 секунд
            handler.postDelayed(this, 30000);
        }
    };

    // Запускаем выполнение Runnable
    handler.post(runnable);
}

    public void onLocationChanged(Location location)
    {
        Location lastlocation = location;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        myMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference EvacuatorAvalablityRef = FirebaseDatabase.getInstance().getReference().child("Evacuator Available");

        GeoFire geoFire = new GeoFire(EvacuatorAvalablityRef);
        geoFire.setLocation(userID, new GeoLocation(location.getLatitude(), location.getLongitude()));
    }


    @Override
    protected void onStop() {
        super.onStop();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference EvacuatorAvalablityRef = FirebaseDatabase.getInstance().getReference().child("Evacuator Available");

        GeoFire geoFire = new GeoFire(EvacuatorAvalablityRef);
        geoFire.removeLocation(userID);
    }

    protected void onDestroy() {
        super.onDestroy();
        // Остановка выполнения кода при уничтожении Activity
        handler.removeCallbacks(runnable);
    }





 //  @Override
 //  public boolean onOptionsItemSelected(MenuItem item) {
 //      int id = item.getItemId(); // Получаем идентификатор выбранного пункта меню
 //      // Определяем действие в зависимости от идентификатора
 //      if (id == R.id.mapNone) {
 //          myMap.setMapType(GoogleMap.MAP_TYPE_NONE);
 //          return true;
 //      } else if (id == R.id.mapNormal) {
 //          myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
 //          return true;
 //      } else if (id == R.id.mapSputnik) {
 //          myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
 //          return true;
 //      } else if (id == R.id.mapHybrid) {
 //          myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
 //          return true;
 //      } else if (id == R.id.mapTerrain) {
 //          myMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
 //          return true;
 //      } else {
 //          return super.onOptionsItemSelected(item);
 //      }
 //  }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                Toast.makeText(this, "В разрешении на определение местоположения отказано, пожалуйста, разрешите определить местоположение", Toast.LENGTH_SHORT).show();
            }
        }
    }




}
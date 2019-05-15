package com.example.thelastversionofprm;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.thelastversionofprm.Utility.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;


public class CameraActivity extends AppCompatActivity {


    private String pathToFile;
    private static final String TAG = "CameraActivity";
    private LocationManager locationManager;
    private String photoLocation = "";
    private double lat;
    private double lon;


    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(GPS_PROVIDER,
                1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(
                NETWORK_PROVIDER, 1000 * 10, 10,
                locationListener);



        lat = Double.valueOf(locationManager.getLastKnownLocation(GPS_PROVIDER).getLatitude());
        lon = Double.valueOf(locationManager.getLastKnownLocation(GPS_PROVIDER).getLongitude());
        photoLocation = hereLocation(lat,lon);
        dispatchPictureTakeAction();
        setupBottomNavigationView();
    }





    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(GPS_PROVIDER,
                1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(
                NETWORK_PROVIDER, 1000 * 10, 10,
                locationListener);


    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @SuppressLint("MissingPermission")
        @Override
        public void onProviderEnabled(String provider) {

            if (ActivityCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
//           showLocation(locationManager.getLastKnownLocation(provider));
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    private void showLocation(Location location) {
        if (location == null)
            return;
        if (location.getProvider().equals(GPS_PROVIDER)) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            photoLocation = hereLocation(location.getLatitude(), location.getLongitude());
            System.out.println("=====>>>>>" + photoLocation);

        } else if (location.getProvider().equals(
                NETWORK_PROVIDER)) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            photoLocation = hereLocation(location.getLatitude(), location.getLongitude());
            System.out.println("------->>>>>" + photoLocation);
        }
    }


    private String hereLocation(double lat, double lon) {
        String cityName = "";
        String countryName = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String result = "";
        try {
            List<Address> addressList = geocoder.getFromLocation(
                    lat, lon, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                }
                sb.append(address.getLocality()).append("_");
                sb.append(address.getCountryName()).append("_")
                ;
                result = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    private void dispatchPictureTakeAction() {
        Intent take = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (take.resolveActivity(getPackageManager()) != null) {
            File photo = null;

            photo = createPhotoFile();

            try {
                if (photo != null) {
                    pathToFile = photo.getAbsolutePath();
                    Uri photoURI = FileProvider.getUriForFile(CameraActivity.this, "com.example.thelastversionofprm.provider", photo);
                    take.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(take, 1);

                }
            } catch (Exception e) {
                System.out.println("to do smth here");
            }
            System.out.println("====----!!>>" + photoLocation );
        }
    }



    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "my_folder");

        File image = null;


        try {
            image = File.createTempFile(name + "_" + photoLocation, ".jpg", folder);

        }catch (IOException e){
            e.printStackTrace();
        }

        return image;
    }





    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting bottomNaviView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(CameraActivity.this, bottomNavigationViewEx);
    }
}


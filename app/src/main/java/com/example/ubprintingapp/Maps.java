package com.example.ubprintingapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.params.HttpConnectionParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Maps extends FragmentActivity implements

        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
     LatLng capen = new LatLng(43.001000, -78.789700);
    private static final int Request_User_Location_Code =99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_estimate);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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






        //adding marker on Capen location

            mMap.addMarker(new MarkerOptions().position(capen).title("Capen"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(capen));

        // getting user's location
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){



            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);





        }
    }

    public boolean checkUserLocationPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        }
        else{
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case Request_User_Location_Code:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                        if(googleApiClient==null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }

    }

    protected synchronized void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
        .addApi(LocationServices.API)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .build();

        googleApiClient.connect();

    }




        @Override
        public void onLocationChanged(Location location) {
        lastLocation = location;
        if(currentUserLocationMarker!=null){
            currentUserLocationMarker.remove();
        }

            LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            //adding violet marker
        markerOptions.position(current);
        markerOptions.title("current location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));


        currentUserLocationMarker =mMap.addMarker(markerOptions);


        mMap.moveCamera(CameraUpdateFactory.zoomBy(18));


            String url = "http://maps.googleapis.com/maps/direction/json?"+
                    "origin="+location.getLatitude() +","+location.getLongitude()+"&"+
                   "destination=" + capen.latitude +","+ capen.longitude+"&sensor=false&"+"mode=walking";

            HttpURLConnection httpURLConnection = null;
            String response = "";
            InputStream input = null;


            try{
                URL urlTemp = new URL(url);
                httpURLConnection = (HttpURLConnection) urlTemp.openConnection();
                httpURLConnection.connect();

                //get response

                input = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader((input));
                BufferedReader bufferedReader = new BufferedReader((inputStreamReader));

                StringBuffer stringBuffer = new StringBuffer();
                String string = "";
                while((string= bufferedReader.readLine())!=null){
                    stringBuffer.append(string);
                }

                response = stringBuffer.toString();
                bufferedReader.close();




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {

                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                httpURLConnection.disconnect();
            }



          //      String urlString = "http://maps.google.com/maps?f=d&hl=en&" +"saddr="+location.getLatitude() +","+location.getLongitude()+"&daddr="+capen.latitude +","+ capen.longitude+ "&ie=UTF8&0&om=0&output=kml";




            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(current, capen)
                    .width(5)
                    .color(Color.BLUE).geodesic(true));





        if(googleApiClient!=null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);

        }

        }




    @Override
        public void onConnected(@Nullable Bundle bundle) {

locationRequest = new LocationRequest();
locationRequest.setInterval(1100);
locationRequest.setFastestInterval(1100);
locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {


    LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
}


        }



        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }
    }

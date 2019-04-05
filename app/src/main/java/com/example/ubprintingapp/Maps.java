package com.example.ubprintingapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//Author: Maria Anikina



public class Maps extends FragmentActivity implements

        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {





    private GoogleMap mMap;
    ArrayList<LatLng> MarkerPoints;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;

    LatLng capen = new LatLng(43.001000, -78.789700);
    LatLng lockwood = new LatLng(43.0003, -78.7860);
    LatLng library = lockwood;


    private static final int Request_User_Location_Code = 99;

    String distance = "";
    String duration = "";

    private String libname = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_estimate);



        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        // Initializing
        MarkerPoints = new ArrayList<>();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //we need it for getMaps method
        //here we are checking which library the user choose to see the duration time and distance.

        if(libname.equals("capen")){
            library = capen;
            //if user choose capen library
        }else {
            //lockwood library
            library = lockwood;
        }

        library = capen;

        //adding marker on library location

        mMap.addMarker(new MarkerOptions().position(library).title("library"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(library));

        mMap.moveCamera(CameraUpdateFactory.zoomBy(12));

        // need for user's location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);


        }

    }
    //adding current user location with marker
    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if (currentUserLocationMarker != null) {
            currentUserLocationMarker.remove();
        }

        LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        //adding violet marker
        markerOptions.position(current);
        markerOptions.title("current location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));




        currentUserLocationMarker = mMap.addMarker(markerOptions);



        LatLng from = currentUserLocationMarker.getPosition();
        LatLng to = library;


        // get url for directions
        String url = gettingUrl(from, to);

        //download data from google
        FetchUrl FetchUrl = new FetchUrl();
        FetchUrl.execute(url);

    }



    public boolean checkUserLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        } else {
            return true;
        }
    }

    //permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (googleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }

    }


    //creating API client
    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        googleApiClient.connect();

    }



    private String gettingUrl(LatLng from, LatLng to) {

        // Creating URL
        String url = "https://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + from.latitude + "," + from.longitude + "&"
                + "destination=" + to.latitude + "," + to.longitude + "&sensor=false"
                +"&key=AIzaSyDhGmwGw20pQorGRWQc2tEnrr53HwAkCUU";



        return url;
    }

    //downloading data from url
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlconnect = null;
        try {
            URL url = new URL(strUrl);
            //Creating HttpURLConnection for access to url

            urlconnect = (HttpURLConnection) url.openConnection();
            urlconnect.connect(); //connecting to url

            //reading
            inputStream = urlconnect.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            data = stringBuffer.toString();
            Log.d("downloadUrl", data.toString());
            bufferedReader.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            inputStream.close();
            urlconnect.disconnect();
        }
        return data;
    }

    // Taking and placing data from URL
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // String where I will put data from url
            String stringUrlData = "";


            // Taking data from url
            try {
                stringUrlData = downloadUrl(url[0]);
                Log.d("Background Task data", stringUrlData.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return stringUrlData;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            PTask pTask = new PTask();
            pTask.execute(result);



        }
    }

    // This is a class which is helping to place locations in right format
    //I need it for creating direction on the map

    private class PTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                Parser parser = new Parser();
                Log.d("ParserTask", parser.toString());


                //start parsing from here
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {

            }
            return routes;
        }

        // Need after parsing
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;




            // for all routes
            for (int a = 0; a < result.size(); a++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = result.get(a);

                // for all points
                for (int b = 0; b < path.size(); b++) {
                    HashMap<String, String> point = path.get(b);


                    //Getting distance
                    if(b==0){
                        distance = (String)point.get("distance");

                        continue;

                        //Getting duration
                    }else if(b==1){
                        duration = (String)point.get("duration");
                        continue;
                    }


                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);

                }

                //putting all points together and creating a blue line.

                lineOptions.addAll(points);
                lineOptions.width(12);
                lineOptions.color(Color.BLUE);


            }



            // Creating a polyline on the map
            if(lineOptions != null) {

                mMap.addPolyline(lineOptions);

                //checking if duration and distance are working
                System.out.println("Duration is " + duration);
                System.out.println("Distance is " + distance);


            }
            else {
                Log.d("onPostExecute","no polyline");
            }
        }
    }


    // method which will return distance and duration (we need it to use the data on others classes)
    public ArrayList<String> getMaps(String libaryname){
        ArrayList<String> data = new ArrayList<String>();
        libname = libaryname;
        data.add(distance);
        data.add(duration);
        return data;
    }


    @Override
    public void onConnected(Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

    }

    @Override

    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // checking location permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                //requesting user location code
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Request_User_Location_Code);


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Request_User_Location_Code);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

}
package com.example.ubprintingapp;


import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//need for map

    public class Parser {


        //by using JsonObject I'm getting all routes and result will be a list <list> with lat and long

        public List<List<HashMap<String,String>>> parse(JSONObject jObject){

            List<List<HashMap<String, String>>> routes = new ArrayList<>() ;

            JSONArray jsonArraySteps;
            JSONArray jsonArrayRoutes;
            JSONArray jsonArrayLegs;
            JSONObject jsonObjectDistance;
            JSONObject jsonObjectDuration;


            try {

                jsonArrayRoutes = jObject.getJSONArray("routes");

                //for routes
                for(int a=0;a<jsonArrayRoutes.length();a++){
                    jsonArrayLegs = ( (JSONObject)jsonArrayRoutes.get(a)).getJSONArray("legs");
                    List path = new ArrayList<>();

                    //for legs
                    for(int b=0;b<jsonArrayLegs.length();b++){



                        //Get Distance
                         jsonObjectDistance = ((JSONObject) jsonArrayLegs.get(b)).getJSONObject("distance");
                        HashMap<String, String> hashMapDistance = new HashMap<String, String>();
                        hashMapDistance.put("distance", jsonObjectDistance.getString("text"));


//I add the distance to the path
                        path.add(hashMapDistance);




                        //end new



                        jsonArraySteps = ( (JSONObject)jsonArrayLegs.get(b)).getJSONArray("steps");

                        //steps
                        for(int c=0; c<jsonArraySteps.length(); c++){
                            String polyline = "";
                            polyline = (String)((JSONObject)((JSONObject)jsonArraySteps.get(c)).get("polyline")).get("points");
                            List<LatLng> list = decoder(polyline);

                            //for point
                            for(int d=0;d<list.size();d++){

                                HashMap<String, String> hm = new HashMap<>();
                                hm.put("lat", Double.toString((list.get(d)).latitude) );
                                hm.put("lng", Double.toString((list.get(d)).longitude) );
                                path.add(hm);
                            }
                        }
                        routes.add(path);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }catch (Exception e){
            }


            return routes;
        }


        private List<LatLng> decoder(String stringCode) {

            List<LatLng> Listpoly = new ArrayList<>();
            int idx = 0, len = stringCode.length();
            int lat = 0, lng = 0;

            while (idx < len) {
                int b, shift = 0, result = 0;
                do {
                    b = stringCode.charAt(idx++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = stringCode.charAt(idx++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)),
                        (((double) lng / 1E5)));
                Listpoly.add(p);
            }

            return Listpoly;
        }
    }









package com.example.ubprintingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ubprintingapp.R;

public class Fragment_music extends Fragment {
    public void showonmapmusic(View v){ //onClick open map activity
        Intent intent = new Intent(getActivity(), Maps.class);
        startActivity(intent);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_music, container,
                false);
        try {
            Bundle gotgift = getArguments();
            String eta = gotgift.getString("ETA") + " Seconds";
            TextView etatoshow = rootView.findViewById(R.id.textView4);
            etatoshow.setText(eta);
        } catch (NullPointerException nullex){
            System.out.println("No Data from ETA Algo!");
        }



        return rootView;
    }
}

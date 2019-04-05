package com.example.ubprintingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ubprintingapp.R;


    public class Fragment_lockwood extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.fragment_lockwood, container,
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




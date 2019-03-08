package com.example.ubprintingapp;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment_capen extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_capen, container,
                false);
        TextView eta = (TextView) rootView.findViewById(R.id.textView4);


        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_capen, container, false);
        return rootView;
    }
}

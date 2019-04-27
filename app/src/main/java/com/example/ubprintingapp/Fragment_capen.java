package com.example.ubprintingapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ubprintingapp.R;


public class Fragment_capen extends Fragment {

    public void showonmapcapen(){ //onClick open map activity
        Intent intent = new Intent(getActivity(), Maps.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        intent.putExtras(b);
        startActivity(intent);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_capen, container,
                false);
        try {
            Bundle gotgift = getArguments();
            String eta = gotgift.getString("ETA") + " Seconds";
            TextView etatoshow = rootView.findViewById(R.id.textView4);
            etatoshow.setText(eta);
        } catch (NullPointerException nullex){
            System.out.println("No Data from ETA Algo!");
        }

        Button capenbutton =  rootView.findViewById(R.id.capbutton);
        capenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showonmapcapen();
            }
        }

        );


        return rootView;
    }
}
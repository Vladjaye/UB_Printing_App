package com.example.ubprintingapp;

import android.content.Context;
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

import java.util.Random;


public class Fragment_lockwood extends Fragment {
        public void showonmaplockwood(View v){ //onClick open map activity
            Intent intent = new Intent(getActivity(), Maps.class);
            Bundle b = new Bundle();
            b.putInt("key", 2);
            intent.putExtras(b);
            startActivity(intent);

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.fragment_lockwood, container,
                    false);
            try {
                Bundle gotgift = getArguments();
                String eta = gotgift.getString("Lock") + " Seconds";
                TextView etatoshow = rootView.findViewById(R.id.textView4);
                etatoshow.setText(eta);
                TextView inkchangeactive = rootView.findViewById(R.id.textView52);
                if (generateink() == true){
                    inkchangeactive.setText("INK CHANGE IN PROGRESS");
                }
            } catch (NullPointerException nullex){
                System.out.println("No Data from ETA Algo!");
            }

            Button lockbutton =  rootView.findViewById(R.id.lockbutton);
            lockbutton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   showonmaplockwood(v);
                                               }
                                           }
            );


            return rootView;
        }
    public boolean generateink(){
        Random rand = new Random();
        int random =  rand.nextInt(6);
        if (random == 1){
            return true;
        }
        else{
            return false;
        }
    }
    }




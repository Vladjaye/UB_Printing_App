package com.example.ubprintingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ubprintingapp.R;

import java.util.Random;

public class Fragment_music extends Fragment {
    public void showonmapmusic(View v){ //onClick open map activity
        Intent intent = new Intent(getActivity(), Maps.class);
        Bundle b = new Bundle();
        b.putInt("key", 3);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_music, container,
                false);
        try {
            Bundle gotgift = getArguments();
            String eta = gotgift.getString("Music") + " Seconds";
            TextView etatoshow = rootView.findViewById(R.id.textView4);
            etatoshow.setText(eta);
            TextView inkchangeactive = rootView.findViewById(R.id.textView53);
            if (generateink() == true){
                inkchangeactive.setText("INK CHANGE IN PROGRESS");
            }
        } catch (NullPointerException nullex){
            System.out.println("No Data from ETA Algo!");
        }

        Button musicbutton =  rootView.findViewById(R.id.musicbutton);
        musicbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showonmapmusic(v);
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

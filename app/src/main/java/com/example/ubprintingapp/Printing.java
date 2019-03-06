package com.example.ubprintingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Printing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printing);


    }


    //Author: Vladyslav Iakusevych
    public int calculatetime(int jobcount) {
        int jobsqueue = jobcount;
        int maptime = 0; //trave time to library from Google Maps API
        int result = jobsqueue * 10 + maptime;
        //10 seconds per page
        //20 seconds per job
        //TODO: for sprint #2 - improve algorithm by counting printing time individually for every page among all jobs

        int value = 0;
        //commint test
        return result;
    }

    //Author: Vladyslav Iakusevych
    public int generatequeue() {
        int random = (int) (Math.random() * 10 + 1);
        return random;

        //TODO: Rewrite it for individual pages algorithm
    }
}

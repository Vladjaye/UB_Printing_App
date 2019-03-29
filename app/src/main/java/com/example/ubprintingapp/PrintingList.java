package com.example.ubprintingapp;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
//Author: Vladyslav Iakusevych
public class PrintingList extends AppCompatActivity {  //Activity (Window) For Printing Queue Functionality


     TabLayout tabLayout;
     ViewPager viewPager;
     Pages pageadapt;
     TabItem capen;
     TabItem lockwood;
     TabItem test;




    @Override
    protected void onCreate(Bundle savedInstanceState) { //Window Initialization
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printing);


        capen  = findViewById(R.id.capenlayout);
        lockwood = findViewById(R.id.lockwoodlayout);
        test = findViewById(R.id.testlayout);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id); //get the id of layout
        viewPager = (ViewPager) findViewById(R.id.viewPager); //get the id of viewpager

        Bundle gift = new Bundle(); //To transfer data into Pages.
        gift.putString("ETA", String.valueOf(calculatetime(generatequeue()))); //For now it is a random ETA time inserted into Bundle

        pageadapt = new Pages(getSupportFragmentManager(), tabLayout.getTabCount(), gift);
        viewPager.setAdapter(pageadapt);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { //Click listener for tabs
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout)); //viewpager sync with tablayout tabs






    }


    //Author: Vladyslav Iakusevych
    public int calculatetime(int jobcount) {
        int maptime = 0; //travel time to library from Google Maps API For now it is 0 - waiting for Maria to finish maps functionality.
        int result = jobcount*10+maptime;
        //10 seconds per page
        //20 seconds per job
        //TODO: for sprint #2 - improve algorithm by counting printing time individually for every page among all jobs






        Log.e("ESTIMATE TIME RESULT: ", result + "SECONDS");
        return result;
    }

    //Author: Vladyslav Iakusevych
    public int generatequeue() { //Generates from 1 to 10 for now.
        int random = (int) (Math.random() * 10 + 1);
        return random;

        //TODO: Rewrite it for individual pages algorithm
    }
}

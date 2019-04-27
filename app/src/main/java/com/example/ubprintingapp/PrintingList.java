package com.example.ubprintingapp;

import android.content.Intent;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

//Author: Vladyslav Iakusevych
public class PrintingList extends AppCompatActivity {  //Activity (Window) For Printing Queue Functionality


     TabLayout tabLayout;
     ViewPager viewPager;
     Pages pageadapt;
     TabItem capen;
     TabItem lockwood;
     TabItem music;


    private String capendist;
    private String capentime;
    private String lockdist;
    private String locktime;
    private String musicdist;
    private String musictime;



    @Override
    protected void onCreate(Bundle savedInstanceState) { //Window Initialization
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printing);


        capen  = findViewById(R.id.capenlayout);
        lockwood = findViewById(R.id.lockwoodlayout);
        music = findViewById(R.id.musiclayout);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id); //get the id of layout
        viewPager = (ViewPager) findViewById(R.id.viewPager); //get the id of viewpager

        Bundle gift = new Bundle(); //To transfer data into Pages.
        //gift.putString("ETA", String.valueOf(calculatetime(generatequeue()))); //For now it is a random ETA time inserted into Bundle
        gift.putString("Capen", String.valueOf(calculatetime(generatequeue(), 1))); //Capen Data
        gift.putString("Lock", String.valueOf(calculatetime(generatequeue(), 2))); //Lockwood Data
        gift.putString("Music", String.valueOf(calculatetime(generatequeue(), 3))); //Music Data

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
    public int calculatetime(int jobcount, int library) {

        //10 seconds per page
        //20 seconds per job
            try{
                Intent intent = new Intent(this, Maps.class);
               // startActivity(intent);

               Bundle temp =  intent.getExtras();
               if (library == 1) {
                   capendist = temp.getString("dist");
                   capentime = temp.getString("time");

                   String[] subst = capentime.split(" ");

                   int result = jobcount*10 + Integer.valueOf(subst[0]);
                   Log.e("ESTIMATE TIME RESULT: ", result + "SECONDS");
                   return result;

               }
                if (library == 2) {
                    capendist = temp.getString("dist");
                    capentime = temp.getString("time");
                    int result = jobcount*10 + Integer.valueOf(locktime);
                    Log.e("ESTIMATE TIME RESULT: ", result + "SECONDS");
                    return result;
                }
                if (library == 3) {
                    capendist = temp.getString("dist");
                    capentime = temp.getString("time");
                    int result = jobcount*10 + Integer.valueOf(musictime);
                    Log.e("ESTIMATE TIME RESULT: ", result + "SECONDS");
                    return result;
                }

            }catch (Exception badmapdtime){}

        //int maptime = 0; //travel time to library from Google Maps API For now it is 0 - waiting for Maria to finish maps functionality.
        return jobcount * 10;
    }

    //Author: Vladyslav Iakusevych
    public int generatequeue() { //Random.
        int random = (int) (Math.random() * 90 + 1);
        return random;

        //TODO: Rewrite it for individual pages algorithm
    }
}

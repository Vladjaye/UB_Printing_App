package com.example.ubprintingapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Map;
import java.util.Random;

public class PCList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       String[] myDataset = new String[20];
        /*myDataset[0] = "PC #1";
        myDataset[1] = statustostring(generatepcstatus());
        myDataset[2] = "PC #2";
        myDataset[3] = statustostring(generatepcstatus());
        myDataset[4] = "PC #3";
        myDataset[5] = statustostring(generatepcstatus());
        myDataset[6] = "PC #4";
        myDataset[7] = statustostring(generatepcstatus());
        myDataset[8] = "PC #5";
        myDataset[9] = statustostring(generatepcstatus());
        myDataset[10] = "PC #6";
        myDataset[11] = statustostring(generatepcstatus());
        myDataset[12] = "PC #7";
        myDataset[13] = statustostring(generatepcstatus());
        myDataset[14] = "PC #8";
        myDataset[15] = statustostring(generatepcstatus());
        myDataset[16] = "PC #9";
        myDataset[17] = statustostring(generatepcstatus());
        myDataset[18] = "PC #10";
        myDataset[19] = statustostring(generatepcstatus());
        myDataset[20] = "PC #11";
        myDataset[21] = statustostring(generatepcstatus());
        myDataset[22] = "PC #12";
        myDataset[23] = statustostring(generatepcstatus());
        myDataset[24] = "PC #13";
        myDataset[25] = statustostring(generatepcstatus());
        myDataset[26] = "PC #14";
        myDataset[27] = statustostring(generatepcstatus());;
        myDataset[28] = "PC #15";
        myDataset[29] = statustostring(generatepcstatus());
        myDataset[30] = "PC #16";
        myDataset[31] = statustostring(generatepcstatus());
        myDataset[32] = "PC #17";
        myDataset[33] = statustostring(generatepcstatus());
        myDataset[34] = "PC #18";
        myDataset[35] = statustostring(generatepcstatus());
        myDataset[36] = "PC #19";
        myDataset[37] = statustostring(generatepcstatus());
        myDataset[38] = "PC #20";
        myDataset[39] = statustostring(generatepcstatus());*/
        myDataset[0] = "PC #1: " + statustostring(generatepcstatus());
        myDataset[1] = "PC #2: " + statustostring(generatepcstatus());
        myDataset[2] = "PC #3: " + statustostring(generatepcstatus());
        myDataset[3] = "PC #4: " + statustostring(generatepcstatus());
        myDataset[4] = "PC #5: " + statustostring(generatepcstatus());
        myDataset[5] = "PC #6: " + statustostring(generatepcstatus());
        myDataset[6] = "PC #7: " + statustostring(generatepcstatus());
        myDataset[7] = "PC #8: " + statustostring(generatepcstatus());
        myDataset[8] = "PC #9: " + statustostring(generatepcstatus());
        myDataset[9] = "PC #10: " + statustostring(generatepcstatus());
        myDataset[10] = "PC #11: " + statustostring(generatepcstatus());
        myDataset[11] = "PC #12: " + statustostring(generatepcstatus());
        myDataset[12] = "PC #13: " + statustostring(generatepcstatus());
        myDataset[13] = "PC #14: " + statustostring(generatepcstatus());
        myDataset[14] = "PC #15: " + statustostring(generatepcstatus());
        myDataset[15] = "PC #16: " + statustostring(generatepcstatus());
        myDataset[16] = "PC #17: " + statustostring(generatepcstatus());
        myDataset[17] = "PC #18: " + statustostring(generatepcstatus());
        myDataset[18] = "PC #19: " + statustostring(generatepcstatus());
        myDataset[19] = "PC #20: " + statustostring(generatepcstatus());





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pclist);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AdaptList(myDataset);
        recyclerView.setAdapter(mAdapter);
    }
    // ...


    public int generatepcstatus() { //Generates from 0 to 1 for PC status
        Random rand = new Random();
        int random =  rand.nextInt(2);
        return random;

        //TODO: Rewrite it for individual pages algorithm
    }
    public String statustostring(int status) { //helper to convert 0 and 1 into busy and free
        String ok;

        if (status == 0) {
            return "Busy";
        } else {
            return "Free";
        }
    }
}

package com.example.ubprintingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PCList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pclist);
    }

    public void openMapWindow(View view) { //onClick open map activity
        Intent intent = new Intent(PCList.this, Maps.class);
        startActivity(intent);
    }
}

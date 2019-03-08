package com.example.ubprintingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//Author: Vladyslav Iakusevych
public class MainMenu extends AppCompatActivity { //"Main Menu" of the app.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPrintingWindow(View view) { //onClick Activity opener for "PrintingList" imagebutton.
        Intent intent = new Intent(MainMenu.this, PrintingList.class);
        startActivity(intent);
    }

    public void openPClistWindow(View view) { //onClick Activity opener for "PCList" imagebutton.
        Intent intent = new Intent(MainMenu.this, PCList.class);
        startActivity(intent);
    }
}

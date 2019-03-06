package com.example.ubprintingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.EditText;

//Author: Vladyslav Iakusevych
public class MainActivity extends AppCompatActivity { //"Main Menu" of the app.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPrintingWindow(View view) { //onClick Activity opener for "Printing" imagebutton.
        Intent intent = new Intent(MainActivity.this, Printing.class);
        startActivity(intent);
    }

    public void openPClistWindow(View view) { //onClick Activity opener for "PClist" imagebutton.
        Intent intent = new Intent(MainActivity.this, PClist.class);
        startActivity(intent);
    }
}

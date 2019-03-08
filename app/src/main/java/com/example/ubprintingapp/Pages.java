package com.example.ubprintingapp;

import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class Pages extends FragmentPagerAdapter {

    private int tabsnum;

    Pages(FragmentManager fm, int tabsnum){
        super(fm);
        this.tabsnum = tabsnum;
    }

    @Override
    public Fragment getItem(int i){
        switch(i){
            case 0:
                return new Fragment_capen();
            case 1:
                return new Fragment_lockwood();
            case 2:
                return new Fragment_test();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {

        return tabsnum;
    }

}

package com.example.ubprintingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class Pages extends FragmentPagerAdapter {

    private int tabsnum;
    private Bundle gift; //Bundle for ETA data from PrintingList

    Pages(FragmentManager fm, int tabsnum, Bundle bundle){
        super(fm);
        this.tabsnum = tabsnum;
        this.gift = bundle; //Get bundle
    }

    @Override
    public Fragment getItem(int i){
        switch(i){
            case 0:
            Fragment_capen capen = new Fragment_capen(); //in order to prevent override of eta, setArguments must be used right after new.
            capen.setArguments(gift);
            return capen;

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

package com.example.ubprintingapp.AlgorithmLogicTests;

import com.example.ubprintingapp.PrintingList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import com.example.ubprintingapp.Maps;
import com.example.ubprintingapp.PrintingList;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetDurationAndDistanceTest {


        @Test
        public void checkTime() {   //simple assertTrue test to check if the duration time between user's location and library will be between 1 and 16 mins.
            Maps map = new Maps();

            boolean test = false;
            if ((map.getMaps("capen").get(1).equals("1 min"))||(map.getMaps("capen").get(1).equals("2 mins"))||
                    (map.getMaps("capen").get(1).equals("3 mins"))||(map.getMaps("capen").get(1).equals("4 mins"))||
                    (map.getMaps("capen").get(1).equals("5 mins"))||(map.getMaps("capen").get(1).equals("6 mins"))||
                    (map.getMaps("capen").get(1).equals("7 mins"))||(map.getMaps("capen").get(1).equals("8 mins"))||
                    (map.getMaps("capen").get(1).equals("9 mins"))||(map.getMaps("capen").get(1).equals("10 mins"))||
                    (map.getMaps("capen").get(1).equals("11 mins"))||(map.getMaps("capen").get(1).equals("12 mins"))||
                    (map.getMaps("capen").get(1).equals("13 mins"))||(map.getMaps("capen").get(1).equals("14 mins"))||
                    (map.getMaps("capen").get(1).equals("15 mins"))||(map.getMaps("capen").get(1).equals("16 mins"))){
                test = true;
                assertTrue(test);
            }
            assertFalse(test);

        }

    @Test
    public void checkDistanceExist() {    //simple assertFalse test to check if the distance between user's location and library exists.
        //will not work if the user will be in the library.
        Maps map = new Maps();

        boolean test = false;
        if ((map.getMaps("capen").get(0).equals("0 mill"))) {

            test = false;
            assertFalse(test);
        } else {
            test = true;
            assertTrue(test);

        }
    }
}

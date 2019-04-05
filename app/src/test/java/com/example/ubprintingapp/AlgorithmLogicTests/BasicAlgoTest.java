package com.example.ubprintingapp.AlgorithmLogicTests;


import com.example.ubprintingapp.PrintingList;

import org.junit.Test;

import static org.junit.Assert.*;


public class BasicAlgoTest {
    @Test
    public void algotimecheck() {   //simple assertTrue test to check if algo provides correct printing time based on queue and eta to library.
        PrintingList printactivity = new PrintingList();


        assertEquals(100, printactivity.calculatetime(10));


    }
}
package com.example.ubprintingapp.AlgorithmLogicTests;

import com.example.ubprintingapp.PrintingList;

import org.junit.Test;

import static org.junit.Assert.*;
import com.google.common.truth.TruthJUnit;



public class BasicAlgoTest {
    @Test
    public void algotimecheck() {   //simple assertTrue test to check if algo provides correct printing time based on queue and eta to library.
        PrintingList printactivity = new PrintingList();
        int[] testalgo = new int[3];
        testalgo[0] = 5;
        testalgo[1] = 10;


        assertEquals(190, printactivity.calculatetime(testalgo, 1));
    }
    }
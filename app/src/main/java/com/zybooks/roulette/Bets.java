package com.zybooks.roulette;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;

public class Bets {

    private final int[] regular = new int[38];

    private ArrayList<Integer> redsBlack = new ArrayList<>();
    private ArrayList<Integer> range = new ArrayList<>();
    private ArrayList<Integer> number = new ArrayList<>();
    private ArrayList<Integer> oddsEven = new ArrayList<>();

    private int betAmount = 25;   // default

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int amount) {
        if (amount > 0) {
            betAmount = amount;
        }
    }

    public Bets() {
        for (int i = 0; i < regular.length; i++) {
            regular[i] = i;
        }
    }


    public void setRedsBlack(int which) {
        ArrayList<Integer> temp = new ArrayList<>();

        int[] blacks = {32,19,21,25,34,6,13,11,8,10,24,33,20,31,22,29,28,35,26};
        int[] reds =   {15,4,2,17,37,27,36,30,23,5,16,1,14,9,18,7,12,3};

        if (which == 0) {
            for (int i : reds) temp.add(i);
        } else if (which == 1) {
            for (int i : blacks) temp.add(i);
        }

        redsBlack = temp;
    }


    public void setRange(int which) {
        ArrayList<Integer> temp = new ArrayList<>();

        int[][] ranges = {
                {1,2,3,4,5,6,7,8,9},
                {10,11,12,13,14,15,16,17,18,19},
                {20,21,22,23,24,25,26,27,28,29},
                {30,31,32,33,34,35,36,37}
        };

        if (which >= 0 && which <= 3) {
            for (int num : ranges[which]) temp.add(num);
        }

        range = temp;
    }


    public void setNumber(int which) {
        ArrayList<Integer> temp = new ArrayList<>();

        if (which > 0 && which <= 37) {
            temp.add(which);
        }

        number = temp;
    }
    public void setOddsEven(int choice) {

        ArrayList<Integer> temp = new ArrayList<>();

        if (choice == 0) {           // even
            for (int i = 2; i < 38; i += 2) temp.add(i);
        }
        else if (choice == 1) {      // odd
            for (int i = 1; i < 38; i += 2) temp.add(i);
        }

        oddsEven = temp;
    }


    public HashSet<Integer> calculateBetOutcomes() {

        HashSet<Integer> betting = new HashSet<>();

        if (!redsBlack.isEmpty()) betting.addAll(redsBlack);
        if (!range.isEmpty()) {
            if (betting.isEmpty()) betting.addAll(range);
            else betting.retainAll(range);
        }
        if (!number.isEmpty()) {
            if (betting.isEmpty()) betting.addAll(number);
            else betting.retainAll(number);
        }
        if (!oddsEven.isEmpty()) {
            if (betting.isEmpty()) betting.addAll(oddsEven);
            else betting.retainAll(oddsEven);
        }

        return betting;
    }


    public boolean hitOrNot(int hit) {
        return calculateBetOutcomes().contains(hit);
    }

    public double multiplier() {
        HashSet<Integer> outcomes = calculateBetOutcomes();

        if (outcomes.isEmpty()) return 10000.0;
        double returnVal =  (double) regular.length / outcomes.size();


        return returnVal;
    }
}


package com.zybooks.roulette;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
public class Bets {

    int[] regular = new int[38];

    ArrayList<Integer> redsBlack = new ArrayList<Integer>();
    ArrayList<Integer> range = new ArrayList<Integer>();
    ArrayList<Integer> number = new ArrayList<Integer>();
    ArrayList<Integer> oddsEven = new ArrayList<Integer>();

    public Bets()
    {

        for(int i =0; i < regular.length; i++)
        {
            regular[i] = i;
        }

    }

    public void setRedsBlack(int which)
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();

        int[] blacks = {32,19,21,25,34,6,13,11,8,10,24,33,20,31,22,29,28,35,26};
        int[] red = {15,4,2,17,37,27,36,30,23,5,16,1,14,9,18,7,12,3};

        if(which == 0)
        {
            for(int i : red)
            {
                temp.add(i);
            }

            redsBlack = temp;
        }
        else if(which == 1){
            for(int i : blacks)
            {
                temp.add(i);
            }
        }
        else{
            redsBlack = temp;
        }


    }

    public void setRange(int which)
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();

        int[] range0 = {1,2,3,4,5,6,7,8,9};
        int[] range1 = {10,11,12,13,14,15,16,17,18,19};
        int[] range2 = {20,21,22,23,24,25,26,27,28,29};
        int[] range3 = {30,31,32,33,34,35,36,37};

        if(which == 0)
        {
            for (int j : range0) {
                temp.add(j);
            }

            range = temp;
        }

        if(which == 1)
        {
            for (int j : range1) {
                temp.add(j);
            }

            range = temp;
        }
        if(which == 2)
        {
            for (int j : range2) {
                temp.add(j);
            }

            range = temp;
        }
        if(which == 3)
        {
            for (int j : range3) {
                temp.add(j);
            }

            range = temp;
        }
        if(which == 4)
        {


            range = temp;
        }







    }

    public void setNumber(int which)
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();


        if(which == 0)
        {
           number = temp;
        }
        else {
            temp.add(which);
            number = temp;
        }



    }

    public void setOddsEven(int choice)
    {
    ArrayList<Integer> input = new ArrayList<Integer>();
   // 0 is even
        // 1 is odds

    int tracker = 1;

    if(choice == 2)
    {
        tracker = 50;
    }

    while(tracker < 38)
    {
    if(tracker % 2 == choice)
    {
        input.add(regular[tracker]);
    }


        tracker++;
    }

        oddsEven = input;

    }



    public HashSet<Integer> calculateBetOutcomes()
    {


        HashSet<Integer> betting = new HashSet<>();







        if(!redsBlack.isEmpty()) {
            if (betting.isEmpty()) {
                for (int i = 0; i < redsBlack.size(); i++) {
                    betting.add(redsBlack.get(i));
                }
            } else {
                betting.retainAll(redsBlack);
            }
        }

if(!range.isEmpty()) {
    if (betting.isEmpty()) {
        for (int i = 0; i < range.size(); i++) {
            betting.add(range.get(i));
        }
    } else {
        betting.retainAll(range);
    }
}

if(!number.isEmpty()) {
    if (betting.isEmpty()) {
        for (int i = 0; i < number.size(); i++) {
            betting.add(number.get(i));
        }
    } else {
        betting.retainAll(number);
    }
}

    if (!oddsEven.isEmpty()) {
        if (betting.isEmpty()) {
            for (int i = 0; i < oddsEven.size(); i++) {
                betting.add(oddsEven.get(i));
            }
        } else {
            betting.retainAll(oddsEven);
        }
    }





        return betting;
    }



    public boolean hitOrNot(int hit)
    {
        HashSet<Integer> outcomes = calculateBetOutcomes();
        return outcomes.contains(hit);

    }

    public double multiplier()
    {
        HashSet<Integer> outcomes = calculateBetOutcomes();

        if(outcomes.isEmpty())
        {
        return 10000.0;
        }
        double test = (double) regular.length / outcomes.size();

        Log.d("tester", Double.toString(test));
        return (double) regular.length / outcomes.size();


    }






}

package com.zybooks.roulette;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public void setOddsEven(int choice)
    {
    ArrayList<Integer> input = new ArrayList<Integer>();

    int tracker = 0;
    while(tracker < 38)
    {
        if(tracker % choice == 0)
        {
        input.add(regular[tracker]);
        }

        tracker++;
    }

        oddsEven = input;

    }


    public ArrayList<Integer> calculateBetOutcomes()
    {


        ArrayList<Integer> betting = new ArrayList<Integer>();

        //changing betting to roulette board numbers
        for (int j : regular) {
            betting.add(j);
        }




        for(int i = 0; i < redsBlack.size(); i++)
        {
            betting.remove(redsBlack.get(i));
        }

        for(int i =0; i < range.size(); i++)
        {
            betting.remove(range.get(i));
        }

        for(int i =0; i < number.size(); i++)
        {
            betting.remove(number.get(i));
        }

        for(int i =0; i < oddsEven.size(); i++)
        {
            betting.remove(oddsEven.get(i));
        }




        return betting;
    }

    public boolean hitOrNot(int hit)
    {
        ArrayList<Integer> outcomes = calculateBetOutcomes();
        return outcomes.contains(hit);

    }

    public double multiplier()
    {
        ArrayList<Integer> outcomes = calculateBetOutcomes();

        if(outcomes.isEmpty())
        {
        return 10000.0;
        }

        return (double) regular.length / outcomes.size();


    }






}

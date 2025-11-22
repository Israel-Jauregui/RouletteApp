package com.zybooks.roulette;
import android.content.SharedPreferences;
import android.content.Context;
public class User {



    SharedPreferences currencyTracker;
    SharedPreferences.Editor editor;

    public User(Context context)
    {
    currencyTracker = context.getSharedPreferences("currencyVal",Context.MODE_PRIVATE);
    editor = currencyTracker.edit();
    }


    public int getMoney()
    {


        return currencyTracker.getInt("Money", 500);
    }

    public void setMoney(int moneyParam)
    {
       editor.putInt("Money", moneyParam);
        editor.apply();
    }

    public int getMoneyProgression()
    {

        return currencyTracker.getInt("moneyProgression", 1);
    }

    public void setMoneyProgression(int moneyParam)
    {
        editor.putInt("moneyProgression", moneyParam);
        editor.apply();

    }






}

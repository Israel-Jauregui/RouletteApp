package com.zybooks.roulette;

public class Wheel {

    // European roulette wheel order (0–36)
    public static final int[] Roul_Num = {
            28, 3, 19, 11, 35, 22, 13, 6, 25, 30, 4, 9, 1, 31,
            23, 33, 7, 15, 17, 0, 8, 27, 10, 2, 26, 21, 32, 12,
            29, 5, 14, 24, 16, 34, 18, 36, 20
    };

    public static final float angle_per_slot = 360f / 37f;

    /** Returns index of a roulette number in the array */
    public static int findIndexOfNumber(int number) {
        for (int i = 0; i < Roul_Num.length; i++) {
            if (Roul_Num[i] == number) return i;
        }
        return -1;
    }
}

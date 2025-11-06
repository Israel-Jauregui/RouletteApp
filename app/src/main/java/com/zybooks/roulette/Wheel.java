package com.zybooks.roulette;

public class Wheel {

    // European roulette wheel order (0–36)
    public static final int[] Roul_Num = {
            0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 37, 6, 27, 13, 36,
            11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22,
            18, 29, 7, 28, 12, 35, 3, 26
    };

    public static final float angle_per_slot = 360f / 38f;

    /** Returns index of a roulette number in the array */
    public static int findIndexOfNumber(int number) {
        for (int i = 0; i < Roul_Num.length; i++) {
            if (Roul_Num[i] == number) return i;
        }
        return -1;
    }
}

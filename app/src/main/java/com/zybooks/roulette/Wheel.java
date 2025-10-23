package com.zybooks.roulette;

import java.util.Random;
import android.animation.Animator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class Wheel {
    public static final int[] Roul_Num = {17, 13, 29, 11, 36, 5, 20, 14, 31, 7, 0, 22, 9, 26, 12,
            33, 2, 19, 4, 24, 6, 28, 1, 21, 10, 34, 3, 30, 8, 18, 23, 32, 16, 27, 15, 35, 25, 36, 29
    };
    public int Roulettewheel() {
        Random random = new Random();
        int RandIndex = random.nextInt(Roul_Num.length);
        return Roul_Num[RandIndex];
    };

    public void spinWheel(ImageView test, int number){
        //number of spins on the wheel
        int spinDegrees = 360 * 3 + new Random().nextInt();
        animator.setDuration(5000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    private int findIndexofNum(int number) {
            for(int i = 0; i < Roul_Num.length; i ++);{
               if(Roul_Num[i] == number) return i;
        }
        return -1;
    }
    public static final float angle_per_slot = 360f / 37f;
    public float current_angle = 0f;

}

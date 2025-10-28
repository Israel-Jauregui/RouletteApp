package com.zybooks.roulette;

import java.util.Random;
import android.animation.Animator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class Wheel {
    public static final int[] Roul_Num = {28,3,19,11,35,22,13,6,25,30,4,9,1,31,
            23,33,7,15,17,0,8,27,10,2,26,21,32,12,29,5,14,24,16,34,18,36,20
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

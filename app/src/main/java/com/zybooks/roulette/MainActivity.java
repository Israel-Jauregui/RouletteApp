package com.zybooks.roulette;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;



public class MainActivity extends AppCompatActivity {

    private ImageView rouletteWheel;
    private Button spinButton;
    private TextView resultText;
    private GestureDetector gestureDetector;

    private Random random;

    private int lastDegree = 0;
    private static final int Num_Slots= 38;
    private static final float Slot_Degrees= 360f / Num_Slots;


    private float currentRotation = 0f;

    private static int[] wheelSequence = {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 37, 6, 27, 13, 36,
            11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22,
            18, 29, 7, 28, 12, 35, 3, 26};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rouletteWheel = findViewById(R.id.rouletteTable);
        resultText = findViewById(R.id.balanceText);

        random = new Random();

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
                spinWheel(velocityX, velocityY);
                return true;
            }
        });

        rouletteWheel.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }

    private void spinWheel(float velocityX, float velocityY) {

        float flickPower = Math.abs(velocityX) + Math.abs(velocityY);
        int spinCount = (int) Math.min(20, flickPower / 2000);
        if (spinCount < 5) spinCount = 5;


        int randomSlot = random.nextInt(Num_Slots);
        int finalDegree = (spinCount * 360) + (int)(randomSlot * Slot_Degrees);

        ObjectAnimator rotate = ObjectAnimator.ofFloat(rouletteWheel, "rotation", lastDegree, finalDegree);
        rotate.setDuration(3000);
        rotate.setInterpolator(null);

        rotate.addListener(new Animator.AnimatorListener(){
            @Override
            public void onAnimationStart(Animator animator){
            resultText.setText("Spinning...");
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator){
                int landedSlot = randomSlot;
                resultText.setText("Result: " + landedSlot);
                lastDegree = finalDegree % 360;
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });

        rotate.start();
    }
    private int getIndexOf(int number) {
        for (int i = 0; i < wheelSequence.length; i++) {
            if (wheelSequence[i] == number) return i;
        }
        return -1; // should never happen
    }
}

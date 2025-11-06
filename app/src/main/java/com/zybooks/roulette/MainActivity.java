package com.zybooks.roulette;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView rouletteWheel;
    private TextView resultText;
    private GestureDetector gestureDetector;
    private Random random;

    private static final int NUM_SLOTS = 38;
    private static final float SLOT_DEGREES = 360f / NUM_SLOTS;

    private static final int[] wheelSequence = {
            0, 32, 15, 19, 4, 21, 2, 25, 17, 34,
            37, 6, 27, 13, 36, 11, 30, 8, 23, 10,
            5, 24, 16, 33, 1, 20, 14, 31, 9, 22,
            18, 29, 7, 28, 12, 35, 3, 26
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rouletteWheel = findViewById(R.id.rouletteTable);
        resultText = findViewById(R.id.balanceText);
        random = new Random();


        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                spinWheel(velocityX, velocityY);
                return true;
            }
        });

        rouletteWheel.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }

    private void spinWheel(float velocityX, float velocityY) {
        int randomIndex = random.nextInt(NUM_SLOTS);
        int result = wheelSequence[randomIndex];

        float flickPower = Math.abs(velocityX) + Math.abs(velocityY);
        int spinCount = (int) Math.min(15, flickPower / 1500);
        if (spinCount < 5) spinCount = 5;

        int targetAngle = (int) (360 - (randomIndex * SLOT_DEGREES));
        int finalDegree = (spinCount * 360) + targetAngle;

        ObjectAnimator rotate = ObjectAnimator.ofFloat(rouletteWheel, "rotation", 0f, finalDegree);
        rotate.setDuration(4000);
        rotate.setInterpolator(new DecelerateInterpolator());

        rotate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                resultText.setText("Spinning...");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // Handle 00 as 37 in the array
                String resultLabel = (result == 37) ? "00" : String.valueOf(result);
                resultText.setText("Result: " + resultLabel);
            }

            @Override public void onAnimationCancel(@NonNull Animator animator) {}
            @Override public void onAnimationRepeat(@NonNull Animator animator) {}
        });

        rotate.start();
    }
}

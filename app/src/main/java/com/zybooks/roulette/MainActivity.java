package com.zybooks.roulette;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static com.zybooks.roulette.Wheel.Roul_Num;
import static com.zybooks.roulette.Wheel.angle_per_slot;
import static com.zybooks.roulette.Wheel.findIndexOfNumber;

public class MainActivity extends AppCompatActivity {

    private ImageView rouletteWheel;
    private Button spinButton;
    private TextView resultText;

    private float currentRotation = 0f;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rouletteWheel = findViewById(R.id.rouletteTable);
        spinButton = findViewById(R.id.spinButton);
        resultText = findViewById(R.id.balanceText);

        spinButton.setOnClickListener(v -> spinWheel());
    }

    private void spinWheel() {
        spinButton.setEnabled(false);

        Random random = new Random();
        int winningIndex = random.nextInt(Roul_Num.length);
        int winningNumber = Roul_Num[winningIndex];

        float targetAngle = 360f - (winningIndex * angle_per_slot);
        float rotations = 360 * 5;
        float finalRotation = currentRotation + rotations + targetAngle;

        ObjectAnimator animator = ObjectAnimator.ofFloat(
                rouletteWheel,
                "rotation",
                currentRotation,
                finalRotation
        );

        animator.setDuration(4000);
        animator.setInterpolator(new DecelerateInterpolator());

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                resultText.setText("Spinning...");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                resultText.setText("Result: " + winningNumber);
                spinButton.setEnabled(true);
                currentRotation = finalRotation % 360;
            }

            @Override public void onAnimationCancel(Animator animation) {}
            @Override public void onAnimationRepeat(Animator animation) {}
        });

        animator.start();
    }
}

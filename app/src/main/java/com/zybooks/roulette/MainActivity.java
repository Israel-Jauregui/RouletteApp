package com.zybooks.roulette;

import static com.zybooks.roulette.Wheel.Roul_Num;
import static com.zybooks.roulette.Wheel.angle_per_slot;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.animation.Animator;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Roulettewheel = findViewById(R.id.Roulettewheel);
        spinButton = findViewById(R.id.spinButton);
        resultText = findViewById(R.id.resultText);

        spinButton.setOnClickListener( v -> spinWheel());
    }

    private void spinWheel(){
        spinButton.setEnabled(false);
        Random random = new Random();

        int winningIndex = random.nextInt(Roul_Num.length);
        int winningNum = Roul_Num[winningIndex];

        float targetAngle = 360f - (winningIndex * angle_per_slot);
        float rotations = 360f / 5;


    }

}
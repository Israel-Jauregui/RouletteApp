package com.zybooks.roulette;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;
import android.animation.Animator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.graphics.Color;
import android.view.Window;

import androidx.core.view.WindowCompat;
public class MainActivity extends AppCompatActivity {

    private RouletteWheel rouletteWheel;
    private TextView balanceText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        ImageView wheelView = findViewById(R.id.rouletteTable);
        balanceText = findViewById(R.id.balanceText);
        Button helpButton = findViewById(R.id.helpButton);

        // Keep your fling class untouched — attach it exactly as before
        rouletteWheel = new RouletteWheel(wheelView);

        // Balance already shows "$" from XML; ensure it's set (defensive)
        balanceText.setText("$");

        // 🔹 Open HelpActivity when Help button is pressed
        helpButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        });
    }
}

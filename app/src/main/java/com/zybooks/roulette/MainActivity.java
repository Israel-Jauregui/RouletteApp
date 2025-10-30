package com.zybooks.roulette;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        // Help button launches the HelpActivity
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intent);
        });
    }
}

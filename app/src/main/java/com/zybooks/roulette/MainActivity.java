package com.zybooks.roulette;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RouletteWheel rouletteWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView wheelView = findViewById(R.id.rouletteTable);

        // Attach the fling/spin behavior
        rouletteWheel = new RouletteWheel(wheelView);
    }
}

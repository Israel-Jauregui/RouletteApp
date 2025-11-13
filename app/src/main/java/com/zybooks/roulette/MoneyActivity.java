package com.zybooks.roulette;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;

public class MoneyActivity extends AppCompatActivity {

    private ImageView moneyImage;
    private TextView counterText;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        moneyImage = findViewById(R.id.moneyImage);
        counterText = findViewById(R.id.moneyCounter);


        counterText.setText("$" + counter);

        moneyImage.setOnClickListener(v -> {
            counter++;
            counterText.setText("$" + counter);
            popAnimation(moneyImage);
        });
    }

    private void popAnimation(ImageView view) {
        ScaleAnimation scale = new ScaleAnimation(
                1f, 1.3f,
                1f, 1.3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scale.setDuration(150);
        scale.setRepeatCount(1);
        scale.setRepeatMode(Animation.REVERSE);
        view.startAnimation(scale);
    }
}


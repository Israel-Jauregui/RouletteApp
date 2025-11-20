package com.zybooks.roulette;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;
import android.media.MediaPlayer;
public class MoneyActivity extends AppCompatActivity {

    private ImageView moneyImage;
    private TextView counterText;
    private int counter = 0;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        user = new User(this);

        moneyImage = findViewById(R.id.moneyImage);
        counterText = findViewById(R.id.moneyCounter);


        counterText.setText("$" + user.getMoney());

        moneyImage.setOnClickListener(v -> {
           user.setMoney(user.getMoney() + 1);
            counterText.setText("$" + user.getMoney());
            popAnimation(moneyImage);
        });
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        counterText.setText("$" + user.getMoney());


    }

    private void popAnimation(ImageView view) {
        ScaleAnimation scale = new ScaleAnimation(
                1f, 1.3f,
                1f, 1.3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        MediaPlayer moneySound = MediaPlayer.create(this, R.raw.money_sound);
        moneySound.setVolume(1.0f, 1.0f);
        moneySound.start();

        scale.setDuration(150);
        scale.setRepeatCount(1);
        scale.setRepeatMode(Animation.REVERSE);
        view.startAnimation(scale);
    }
}


package com.zybooks.roulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;
import android.media.MediaPlayer;
public class MoneyActivity extends AppCompatActivity {

    private ImageView moneyImage;
    private TextView counterText;
    private TextView multiplerText;
    private Button moneyUpgradeButton;
    private int counter = 0;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        user = new User(this);

        moneyImage = findViewById(R.id.moneyImage);
        counterText = findViewById(R.id.moneyCounter);
        multiplerText = findViewById(R.id.multiplierText);

        moneyUpgradeButton = findViewById(R.id.upgradeButton);

        counterText.setText("$" + user.getMoney());
        multiplerText.setText("Multipler: x" + user.getMoneyProgression());

        moneyUpgradeButton.setOnClickListener(v ->
        {
                if(user.getMoney() >= 25)
                {
                    int temp  = user.getMoneyProgression();
                    user.setMoneyProgression(temp + 1);
                    multiplerText.setText("Multipler: x" + user.getMoneyProgression());
                    user.setMoney(user.getMoney() - 25);
                    counterText.setText("$" + user.getMoney());



                }
                else{

                    OutOfMoneyFragment dialog = new OutOfMoneyFragment();
                    dialog.show(getSupportFragmentManager(),"OutOfMoney");


                }

        }        );



        moneyImage.setOnClickListener(v -> {
            int multipler = user.getMoneyProgression();


           user.setMoney(user.getMoney() + (multipler));
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


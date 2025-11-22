package com.zybooks.roulette;

import android.content.Intent;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.GestureDetector;
import android.media.MediaPlayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity
        implements OddsEvenDialogFragment.OnOddsEvenSelectedListener,
        RedBlackDialogFragment.OnRedBlackSelectedListener,
        NumberDialogFragment.OnNumberSelectedListener,
        RangeDialogFragment.OnRangeSelectedListener {

    private User user;
    private ImageView rouletteWheel;
    private TextView balanceText;
    private TextView resultText;
    private TextView multiplerText;
    private TextView betAmountText;
    private GestureDetector gestureDetector;
    private Random random;
    private Bets bet;
    private static final int NUM_SLOTS = 38;
    private static final float SLOT_DEGREES = 360f / NUM_SLOTS;

    private void showBetPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_bets, null);

        builder.setView(view);
        AlertDialog dialog = builder.create();

        // Buttons
        view.findViewById(R.id.bet5).setOnClickListener(v -> {
            bet.setBetAmount(5);
            betAmountText.setText("Bet: $5");
            dialog.dismiss();
        });
        view.findViewById(R.id.bet10).setOnClickListener(v -> {
            bet.setBetAmount(10);
            betAmountText.setText("Bet: $10");
            dialog.dismiss();
        });
        view.findViewById(R.id.bet25).setOnClickListener(v -> {
            bet.setBetAmount(25);
            betAmountText.setText("Bet: $25");
            dialog.dismiss();
        });
        view.findViewById(R.id.bet50).setOnClickListener(v -> {
            bet.setBetAmount(50);
            betAmountText.setText("Bet: $50");
            dialog.dismiss();
        });
        view.findViewById(R.id.bet100).setOnClickListener(v -> {
            bet.setBetAmount(100);
            betAmountText.setText("Bet: $100");
            dialog.dismiss();
        });
        view.findViewById(R.id.bet200).setOnClickListener(v -> {
            bet.setBetAmount(200);
            betAmountText.setText("Bet: $200");
            dialog.dismiss();
        });

        dialog.show();
    }

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
        user = new User(this);
        bet = new Bets();
        setContentView(R.layout.activity_main);

        rouletteWheel = findViewById(R.id.rouletteTable);
        multiplerText = findViewById(R.id.betMultiplierText);
        balanceText = findViewById(R.id.MoneyText);
        resultText = findViewById(R.id.balanceText);
        betAmountText = findViewById(R.id.betAmountText);
        random = new Random();

        // Show initial bet
        betAmountText.setText("Bet: $" + bet.getBetAmount());

        Button changeBet = findViewById(R.id.changeBetBtn);
        changeBet.setOnClickListener(v -> showBetPopup());

        Button helpButton = findViewById(R.id.helpButton);
        Button moneyButton = findViewById(R.id.moneyButton);

        balanceText.setText("$" + user.getMoney());

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                if(user.getMoney() > bet.getBetAmount()) {
                    spinWheel(velocityX, velocityY);
                }
                else
                {
                    OutOfMoneyFragment dialog = new OutOfMoneyFragment();
                    dialog.show(getSupportFragmentManager(),"OutOfMoney");
                }
                return true;
            }
        });

        rouletteWheel.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

        helpButton.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HelpActivity.class))
        );
        moneyButton.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MoneyActivity.class))
        );
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        balanceText.setText("$" + user.getMoney());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.roulette_menu, menu);
        return true;
    }

    private void spinWheel(float velocityX, float velocityY) {
        int randomIndex = random.nextInt(NUM_SLOTS);
        int result = wheelSequence[randomIndex];

        float flickPower = Math.abs(velocityX) + Math.abs(velocityY);
        int spinCount = (int) Math.min(15, flickPower / 1500);
        if (spinCount < 5) spinCount = 5;

        int targetAngle = (int) (360 - (randomIndex * SLOT_DEGREES));
        int finalDegree = (spinCount * 360) + targetAngle;

        MediaPlayer spinSound = MediaPlayer.create(this, R.raw.wheel_sound);
        spinSound.setVolume(1.0f, 1.0f);
        spinSound.setLooping(true);
        spinSound.start();

        ObjectAnimator rotate = ObjectAnimator.ofFloat(rouletteWheel, "rotation", 0f, finalDegree);
        rotate.setDuration(4000);
        rotate.setInterpolator(new DecelerateInterpolator());

        rotate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                user.setMoney(user.getMoney() - bet.getBetAmount());
                balanceText.setText("$" + user.getMoney());
                resultText.setText("Spinning...");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                String resultLabel = String.valueOf(result);
                resultText.setText("Result: " + resultLabel);

                int winnings = (int) (bet.multiplier() * bet.getBetAmount());

                if(bet.hitOrNot(result)) {
                    user.setMoney(user.getMoney() + winnings);
                    balanceText.setText("$" + user.getMoney());
                }

                if (spinSound != null) {
                    spinSound.stop();
                    spinSound.release();
                }
            }

            @Override public void onAnimationCancel(@NonNull Animator animator) {}
            @Override public void onAnimationRepeat(@NonNull Animator animator) {}
        });

        rotate.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_red_black) {
            RedBlackDialogFragment dialog = new RedBlackDialogFragment();
            dialog.show(getSupportFragmentManager(), "redBlackDialog");
            return true;
        } else if (id == R.id.action_odd_even) {
            OddsEvenDialogFragment dialog = new OddsEvenDialogFragment();
            dialog.show(getSupportFragmentManager(), "oddsEvenDialog");
            return true;
        } else if (id == R.id.action_number) {
            NumberDialogFragment dialog = new NumberDialogFragment();
            dialog.show(getSupportFragmentManager(), "numberDialog");
            return true;
        } else if (id == R.id.action_range) {
            RangeDialogFragment dialog = new RangeDialogFragment();
            dialog.show(getSupportFragmentManager(), "rangeDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOddsEvenClick(int which) {
        bet.setOddsEven(which);
        multiplerText.setText("Multiplier: x" + bet.multiplier());
    }

    @Override
    public void onNumberSelected(int number) {
        bet.setNumber(number);
        multiplerText.setText("Multiplier: x" + bet.multiplier());
    }

    @Override
    public void onRedBlackClick(int which) {
        bet.setRedsBlack(which);
        multiplerText.setText("Multiplier: x" + bet.multiplier());
    }

    @Override
    public void onNumberClick(int which) {
        bet.setNumber(which);
        multiplerText.setText("Multiplier: x" + bet.multiplier());
    }

    @Override
    public void onRangeClick(int which) {
        bet.setRange(which);
        multiplerText.setText("Multiplier: x" + bet.multiplier());
    }
}
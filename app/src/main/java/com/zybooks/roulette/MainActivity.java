package com.zybooks.roulette;

import android.content.Intent;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.GestureDetector;

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
    private GestureDetector gestureDetector;
    private Random random;
private Bets bet;
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
        user = new User(this);
        bet = new Bets();
        setContentView(R.layout.activity_main);
        rouletteWheel = findViewById(R.id.rouletteTable);

        multiplerText = findViewById(R.id.betMultiplierText);
        balanceText = findViewById(R.id.MoneyText);
        resultText = findViewById(R.id.balanceText);
        random = new Random();



        Button helpButton = findViewById(R.id.helpButton);
        Button moneyButton = findViewById(R.id.moneyButton);

        balanceText.setText("$" + user.getMoney());

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                spinWheel(velocityX, velocityY);
                return true;
            }
        });

        rouletteWheel.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));


        // Help button opens HelpActivity
        helpButton.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HelpActivity.class))
        );
        moneyButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MoneyActivity.class));
        });
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

        ObjectAnimator rotate = ObjectAnimator.ofFloat(rouletteWheel, "rotation", 0f, finalDegree);
        rotate.setDuration(4000);
        rotate.setInterpolator(new DecelerateInterpolator());

        rotate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

                user.setMoney(user.getMoney() - 25);
                balanceText.setText("$" + user.getMoney());
                resultText.setText("Spinning...");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // Handle 00 as 37 in the array
                String resultLabel = (result == 37) ? "00" : String.valueOf(result);
                resultText.setText("Result: " + resultLabel);

                int add = (int) bet.multiplier() * 25;


                if(bet.hitOrNot(result))
                {
                    user.setMoney(user.getMoney() + add);
                    balanceText.setText("$" + user.getMoney());
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
            Toast.makeText(this, "Red / Black selected", Toast.LENGTH_SHORT).show();
            RedBlackDialogFragment dialog = new RedBlackDialogFragment();
            dialog.show(getSupportFragmentManager(), "redBlackDialog");
            return true;
        } else if (id == R.id.action_odd_even) {
            Toast.makeText(this, "Odd / Even selected", Toast.LENGTH_SHORT).show();
            OddsEvenDialogFragment dialog = new OddsEvenDialogFragment();
            dialog.show(getSupportFragmentManager(), "oddsEvenDialog");
            return true;
        } else if (id == R.id.action_number) {
            Toast.makeText(this, "Number selected", Toast.LENGTH_SHORT).show();
            NumberDialogFragment dialog = new NumberDialogFragment();
            dialog.show(getSupportFragmentManager(), "numberDialog");
            return true;
        } else if (id == R.id.action_range) {
            Toast.makeText(this, "Range selected", Toast.LENGTH_SHORT).show();
            RangeDialogFragment dialog = new RangeDialogFragment();
            dialog.show(getSupportFragmentManager(), "rangeDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Called when user picks Odds or Even
    @Override
    public void onOddsEvenClick(int which) {
       // String[] choices = getResources().getStringArray(R.array.oddsEven_array);
       // String choice = choices[which];
       // Toast.makeText(this, "You chose: " + choice, Toast.LENGTH_SHORT).show();

        bet.setOddsEven(which);
        double multiplerval = bet.multiplier();
        multiplerText.setText("Multiplier:x" + Double.toString(multiplerval));


    }

    @Override
    public void onNumberSelected(int number) {

        bet.setNumber(number);
        double multiplerval = bet.multiplier();
        multiplerText.setText("Multiplier:x" + Double.toString(multiplerval));



    }
    // Called when user picks Red or Black
    @Override
    public void onRedBlackClick(int which) {
       // String[] choices = getResources().getStringArray(R.array.redBlack_array);
       // String choice = choices[which];
       // Toast.makeText(this, "You chose: " + choice, Toast.LENGTH_SHORT).show();

        bet.setRedsBlack(which);
        double multiplerval = bet.multiplier();
        multiplerText.setText("Multiplier:x" + Double.toString(multiplerval));
    }

    // Called when user picks a Number
    @Override
    public void onNumberClick(int which) {
     //   String[] numbers = getResources().getStringArray(R.array.number_array);
       // String number = numbers[which];
       // Toast.makeText(this, "You chose number: " + number, Toast.LENGTH_SHORT).show();
        bet.setNumber(which);
        double multiplerval = bet.multiplier();
        multiplerText.setText("Multiplier:x" + Double.toString(multiplerval));

    }

    // Called when user picks a Range
    @Override
    public void onRangeClick(int which) {
     //   String[] ranges = getResources().getStringArray(R.array.range_array);
       // String range = ranges[which];
       // Toast.makeText(this, "You chose range: " + range, Toast.LENGTH_SHORT).show();
        bet.setRange(which);
        double multiplerval = bet.multiplier();
        multiplerText.setText("Multiplier:x" + Double.toString(multiplerval));


    }
}
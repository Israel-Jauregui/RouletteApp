package com.zybooks.roulette;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements OddsEvenDialogFragment.OnOddsEvenSelectedListener,
        RedBlackDialogFragment.OnRedBlackSelectedListener,
        NumberDialogFragment.OnNumberSelectedListener,
        RangeDialogFragment.OnRangeSelectedListener {

    private User user;
    private RouletteWheel rouletteWheel;
    private TextView balanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User(this);
        setContentView(R.layout.activity_main);

        ImageView wheelView = findViewById(R.id.rouletteTable);
        balanceText = findViewById(R.id.MoneyText);
        Button helpButton = findViewById(R.id.helpButton);
        Button moneyButton = findViewById(R.id.moneyButton);

        rouletteWheel = new RouletteWheel(wheelView);
        balanceText.setText("$" + user.getMoney());

        // Help button opens HelpActivity
        helpButton.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HelpActivity.class))
        );
        moneyButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MoneyActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.roulette_menu, menu);
        return true;
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
        String[] choices = getResources().getStringArray(R.array.oddsEven_array);
        String choice = choices[which];
        Toast.makeText(this, "You chose: " + choice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNumberSelected(int number) {

    }
    // Called when user picks Red or Black
    @Override
    public void onRedBlackClick(int which) {
        String[] choices = getResources().getStringArray(R.array.redBlack_array);
        String choice = choices[which];
        Toast.makeText(this, "You chose: " + choice, Toast.LENGTH_SHORT).show();
    }

    // Called when user picks a Number
    @Override
    public void onNumberClick(int which) {
        String[] numbers = getResources().getStringArray(R.array.number_array);
        String number = numbers[which];
        Toast.makeText(this, "You chose number: " + number, Toast.LENGTH_SHORT).show();
    }

    // Called when user picks a Range
    @Override
    public void onRangeClick(int which) {
        String[] ranges = getResources().getStringArray(R.array.range_array);
        String range = ranges[which];
        Toast.makeText(this, "You chose range: " + range, Toast.LENGTH_SHORT).show();
    }
}
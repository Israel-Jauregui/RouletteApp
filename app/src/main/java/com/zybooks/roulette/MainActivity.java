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

public class MainActivity extends AppCompatActivity implements OddsEvenDialogFragment.OnOddsEvenSelectedListener {

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

        helpButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        });

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
            int newMoney = user.getMoney() + 1;
            user.setMoney(newMoney);
            balanceText.setText("$" + user.getMoney());
            Toast.makeText(this, "Red / Black selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_range) {
            Toast.makeText(this, "Range selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_number) {
            Toast.makeText(this, "Number selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_odd_even) {
            Toast.makeText(this, "Odd / Even selected", Toast.LENGTH_SHORT).show();
            OddsEvenDialogFragment dialog = new OddsEvenDialogFragment();
            dialog.show(getSupportFragmentManager(), "oddsEvenDialog");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOddsEvenClick(int which) {

    }
}

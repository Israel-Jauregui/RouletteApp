package com.zybooks.roulette;
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


public class MainActivity extends AppCompatActivity {

    private static final int[] Roul_Num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36

    };

    public int wheelspin() {
        Random random = new Random();
        int RandIndex = random.nextInt(Roul_Num.length);
        return Roul_Num[RandIndex];
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // This shows the icons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.roulette_menu, menu);
        return true;
    }
    // This Handles clicks on each AppBar icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_red_black) {
            Toast.makeText(this, "Red / Black clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_range) {
            Toast.makeText(this, "Range clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_number) {
            Toast.makeText(this, "Number clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_odd_even) {
            Toast.makeText(this, "Odd / Even clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



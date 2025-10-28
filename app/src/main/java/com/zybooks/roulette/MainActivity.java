package com.zybooks.roulette;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
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



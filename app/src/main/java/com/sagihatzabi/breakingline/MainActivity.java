package com.sagihatzabi.breakingline;

import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;

import com.sagihatzabi.breakingline.items.Burger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int MAX_NUMBER_OF_ITEMS = 3;
        final int NUMBER_OF_ITEMS = 3;

        // Add menu programmatically
        final LinearLayout menu = new LinearLayout(this);
        menu.setOrientation(LinearLayout.VERTICAL);

        // Calculate the icons size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int iconSize = NUMBER_OF_ITEMS <= MAX_NUMBER_OF_ITEMS ?
                    width / NUMBER_OF_ITEMS :
                    width / MAX_NUMBER_OF_ITEMS;

        // Add burger
        menu.addView(Burger.create(this)
                           .setType(Burger.Type.BeefBurger)
                           .setSize(iconSize, iconSize)
                           .setName("Sagi Test1")
                           .setPriceInDollars(9.99f)
                           .removeCheese()
                           .build());

        // Add custom burgerslist
        menu.addView(BurgersRecyclerView.create(this).build());

        // Add the menu to activity
        ((ConstraintLayout) findViewById(R.id.activity_main)).addView(menu);
    }
}

package com.sagihatzabi.breakingline;

import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.widget.LinearLayout;

import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;
import java.util.List;

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

        // Create FoodRecyclerView
        BurgersRecyclerView mRecyclerView = new BurgersRecyclerView(this);
        mRecyclerView.enableViewScaling(false);
        mRecyclerView.enableAlphaViews(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<SagiVectorIcon> customViews = new ArrayList<>();

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
        customViews.add(Burger.create(this)
                           .setType(Burger.Type.BeefBurger)
                           .setSize(iconSize, iconSize)
                           .setName("Sagi Test1")
                           .setPriceInDollars(9.99f)
                           .removeCheese()
                           .build());

        // Add burger 2
        customViews.add(Burger.create(this)
                .setType(Burger.Type.ChikenBurger)
                .setSize(iconSize, iconSize)
                .setName("Sagi Test2")
                .setPriceInDollars(0f)
                .removeCheese()
                .build());

        // Add burger 3
        customViews.add(Burger.create(this)
                .setType(Burger.Type.NoSesameBurger)
                .setSize(iconSize, iconSize)
                .setName("Sagi Test3")
                .setPriceInDollars(12.99f)
                .removeCheese()
                .build());

        // Add burger 4
        customViews.add(Burger.create(this)
                .setType(Burger.Type.NoVeggAndCheeseBurger)
                .setSize(iconSize, iconSize)
                .setName("Sagi Test4")
                .setPriceInDollars(6.99f)
                .removeCheese()
                .build());

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // specify an adapter (see also next example)
        FoodExtraAdapter mAdapter = new FoodExtraAdapter(customViews);
        mRecyclerView.setAdapter(mAdapter);

        // Add custom burgerslist
        menu.addView(FoodRecyclerView.create(this).build());

        // Add the menu to activity
        ((ConstraintLayout) findViewById(R.id.activity_main)).addView(menu);
    }
}

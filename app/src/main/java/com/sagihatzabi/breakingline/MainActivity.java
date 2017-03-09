package com.sagihatzabi.breakingline;

import android.graphics.Point;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.widget.LinearLayout;

import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.FoodExtra;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int NUM_OF_ITEMS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add menu programmatically
        final LinearLayout menu = new LinearLayout(this);
        menu.setOrientation(LinearLayout.VERTICAL);

        for (int i=1; i<=5; i++) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.addView(createSnappingRecyclerView());
                }
            }, i * 500);
        }

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        nestedScrollView.addView(menu);
        ((LinearLayout) findViewById(R.id.activity_main)).addView(nestedScrollView);
//
//        LinearLayout line1 = (LinearLayout) findViewById(R.id.line1);
//
//        Burger burger = new Burger(this, Burger.Type.BeefBurger);
//        line1.addView(burger);
//        burger.setOnClickListener(this);
//
//        burger = new Burger(this, Burger.Type.NoCheeseBurger);
//        line1.addView(burger);
//        burger.setOnClickListener(this);
//
//        burger = new Burger(this, Burger.Type.NoVeggBurger);
//        line1.addView(burger);
//        burger.setOnClickListener(this);
    }

    BurgersRecyclerView createSnappingRecyclerView() {
        List<SagiVectorIcon> customViews = new ArrayList<>();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int iconSize = width/3;

//        customViews.add(new Burger(this, Burger.Type.BeefBurger, iconSize, iconSize));
        customViews.add(Burger.create(this)
                .setType(Burger.Type.BeefBurger)
                .setSize(iconSize, iconSize)
                .setName("Sagi Test1")
                .setPriceInDollars(9.99f)
                .setDescription("Description")
                .removeCheese()
                .build());

        // specify an adapter (see also next example)
        FoodAdapter mAdapter = new FoodAdapter(customViews);

        // Create FoodRecyclerView
        BurgersRecyclerView mRecyclerView =
                BurgersRecyclerView.create(this)
                        .addAdapter(mAdapter)
                        .setNumberOfItems(NUM_OF_ITEMS)
                        .setOrientation(LinearLayoutManager.HORIZONTAL)
                        .build();


        return mRecyclerView;
    }

//    final int NUM_OF_EXTRAS = 4;
//
//    // Add menu programmatically
//    final LinearLayout menu = new LinearLayout(this);
//        menu.setOrientation(LinearLayout.VERTICAL);
//
//    List<SagiVectorIcon> customViews = new ArrayList<>();
//
//    // Calculate the icons size
//    Display display = getWindowManager().getDefaultDisplay();
//    Point size = new Point();
//        display.getSize(size);
//    int width = size.x;
//    int height = size.y;
//    int iconSize = width / NUM_OF_EXTRAS;
//
//        customViews.add(FoodExtra.create(this)
//                .setType(FoodExtra.Type.Veggs)
//                .setSize(iconSize, 330)
//                .build());
//
//        customViews.add(FoodExtra.create(this)
//                .setType(FoodExtra.Type.Cheese)
//                .setSize(iconSize, 330)
//                .build());
//
//        customViews.add(FoodExtra.create(this)
//                .setType(FoodExtra.Type.Bacon)
//                .setSize(iconSize, 330)
//                .build());
//
//        customViews.add(FoodExtra.create(this)
//                .setType(FoodExtra.Type.Chili)
//                .setSize(iconSize, 330)
//                .build());
//
//        customViews.add(FoodExtra.create(this)
//                .setType(FoodExtra.Type.Egg)
//                .setSize(iconSize, 330)
//                .build());
//
//    // specify an adapter (see also next example)
//    FoodExtraAdapter mAdapter = new FoodExtraAdapter(customViews);
//
//
//    // Create FoodRecyclerView
//    BurgersRecyclerView mRecyclerView =
//            BurgersRecyclerView.create(this)
//                    .addAdapter(mAdapter)
//                    .setNumberOfItems(NUM_OF_EXTRAS)
//                    .setOrientation(LinearLayoutManager.HORIZONTAL)
//                    .build();
//
//
//        mRecyclerView.setAdapter(mAdapter);
//
//    // Add custom burgerslist
//        menu.addView(mRecyclerView);
//
//    // Add the menu to activity
//        ((ConstraintLayout) findViewById(R.id.activity_main)).addView(menu);
}

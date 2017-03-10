package com.sagihatzabi.breakingline;

import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.Fries;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodAdapter.OnItemClickListener,
        CartFragment.OnFragmentInteractionListener {

    final int NUM_OF_ITEMS = 3;

    private LinearLayout mLinearLayout;
    private PopupWindow mPopupWindow;
    View mCartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Fragment
        mCartFragment = findViewById(R.id.main_activity_cart_fragment);

        mLinearLayout = (LinearLayout) findViewById(R.id.main_activity_inner_layout);

        // Add menu programmatically
        final LinearLayout menu = new LinearLayout(this);
        menu.setOrientation(LinearLayout.VERTICAL);

        for (int i = 1; i <= 5; i++) {
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
        mLinearLayout.addView(nestedScrollView);
    }

    BurgersRecyclerView createSnappingRecyclerView() {
        List<SagiVectorIcon> customViews = new ArrayList<>();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int iconSize = width / 3;

//        customViews.add(new Burger(this, Burger.Type.BeefBurger, iconSize, iconSize));
        customViews.add(Burger.create(this)
                .setType(Burger.Type.BeefBurger)
                .setSize(iconSize, iconSize)
                .setName("Sagi Test1")
                .setPriceInDollars(9.99f)
                .setDescription("Description")
                .removeCheese()
                .build());

        customViews.add(Burger.create(this)
                .setType(Burger.Type.ChikenBurger)
                .setSize(iconSize, iconSize)
                .setName("Sagi Test2")
                .setPriceInDollars(8.99f)
                .setDescription("Description")
                .build());

        customViews.add(Fries.create(this)
                .setType(Fries.Type.RegularFries)
                .setSize(iconSize, iconSize)
                .setDescription("Description")
                .build());

        customViews.add(Fries.create(this)
                .setType(Fries.Type.SweetPotatoFries)
                .setSize(iconSize, iconSize)
                .setDescription("Description")
                .build());

        customViews.add(Fries.create(this)
                .setType(Fries.Type.HotFries)
                .setSize(iconSize, iconSize)
                .setDescription("Description")
                .build());

        // specify an adapter (see also next example)
        FoodAdapter mAdapter = new FoodAdapter(customViews, this);

        // Create FoodRecyclerView
        BurgersRecyclerView mRecyclerView =
                BurgersRecyclerView.create(this)
                        .addAdapter(mAdapter)
                        .setNumberOfItems(NUM_OF_ITEMS)
                        .setOrientation(LinearLayoutManager.HORIZONTAL)
                        .build();


        return mRecyclerView;
    }

    /**
     * OnitemClick from recyclerview
     */
    @Override
    public void onItemClick(SagiVectorIcon vectorIcon) {
        showPopUpWindow(vectorIcon);
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

    private void showPopUpWindow(SagiVectorIcon vectorIcon) {
        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom view
        View popup_window_layout = inflater.inflate(R.layout.food_details, null);

        // Food
        View localFoodView = null;

        if (vectorIcon instanceof Burger) {
            localFoodView = Burger.create(this)
                    .setType(((Burger) vectorIcon).mType)
                    .setSize(480, 480)
                    .build();

        }
        else if (vectorIcon instanceof Fries) {
            localFoodView = Fries.create(this)
                    .setType(((Fries) vectorIcon).mType)
                    .setSize(480, 480)
                    .build();
        }


        ViewGroup.LayoutParams localburgerLayoutParams = localFoodView.getLayoutParams();
        RelativeLayout.LayoutParams burgerRelativeLayoutParams =
                new RelativeLayout.LayoutParams(localburgerLayoutParams.width, localburgerLayoutParams.height);
        burgerRelativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        burgerRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        final float scale = getResources().getDisplayMetrics().density;
        int margin_24 = (int) (24 * scale + 0.5f);
        burgerRelativeLayoutParams.topMargin = margin_24;

        // Add burger to customView
        ((RelativeLayout) popup_window_layout.findViewById(R.id.food_details_outer_layout)).addView(localFoodView, burgerRelativeLayoutParams);

        TextView tvPrice = (TextView) popup_window_layout.findViewById(R.id.food_details_price);
        TextView tvName = (TextView) popup_window_layout.findViewById(R.id.food_details_name);
        TextView tvDescription = (TextView) popup_window_layout.findViewById(R.id.food_details_description);

        tvPrice.setText(vectorIcon.mPrice + getString(R.string.dollar_sign));
        tvName.setText(vectorIcon.mName);
        tvDescription.setText(vectorIcon.mDescription);

        TextView btnAddToCart = (TextView) popup_window_layout.findViewById(R.id.food_details_add_cart);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Pass Details back to activity


                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        });

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                popup_window_layout,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        mPopupWindow.setElevation(5.0f);

        // Animation
        mPopupWindow.setAnimationStyle(R.style.PopUpAnimation);

//        // Get a reference for the custom view close button
//        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
//
//        // Set a click listener for the popup window close button
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Dismiss the popup window
//                mPopupWindow.dismiss();
//            }
//        });

                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onBackPressed() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package com.sagihatzabi.breakingline;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.ColaCan;
import com.sagihatzabi.breakingline.items.ColaCan2;
import com.sagihatzabi.breakingline.items.FoodExtra;
import com.sagihatzabi.breakingline.items.FoodExtraState;
import com.sagihatzabi.breakingline.items.Fries;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;
import com.sagihatzabi.breakingline.items.SodaCan;
import com.sagihatzabi.breakingline.items.Steak;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodAdapter.OnItemClickListener,
        FoodExtraAdapter.OnItemClickListener,
        CartFragment.OnFragmentInteractionListener {


    final int NUM_OF_ITEMS = 3;

    int screenWidth;
    int screenHeight;

    private LinearLayout mLinearLayout;
    private PopupWindow mPopupWindow;
    private CartFragment mCartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Fragment
        mCartFragment = (CartFragment) getSupportFragmentManager().findFragmentById(R.id.main_activity_cart_fragment);

        mLinearLayout = (LinearLayout) findViewById(R.id.main_activity_inner_layout);

        // Add menu programmatically
        final LinearLayout menu = new LinearLayout(this);
        menu.setOrientation(LinearLayout.VERTICAL);

        for (int i = 1; i <= 4; i++) {
            final Handler handler = new Handler();
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.addView(addLine(finalI));
                }
            }, i * 600);
        }

        NestedScrollView nestedScrollView = new NestedScrollView(this);
        nestedScrollView.addView(menu);
        mLinearLayout.addView(nestedScrollView);
    }

    BurgersRecyclerView addLine(int lineNumber) {
        List<SagiVectorIcon> customViews = new ArrayList<>();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        int iconSize = screenWidth / NUM_OF_ITEMS;

        switch (lineNumber) {
            case 1: {
                customViews.add(Steak.create(this)
                        .setType(Steak.Type.Steak)
                        .setSize(iconSize, iconSize)
                        .setName("Sinta Steak")
                        .setDescription(getString(R.string.burger_description))
                        .build());

                customViews.add(Burger.create(this)
                        .setType(Burger.Type.BeefBurger)
                        .setSize(iconSize, iconSize)
                        .setName("Burger")
                        .setPriceInDollars(9.99f)
                        .setDescription(getString(R.string.burger_description))
                        .build());

                customViews.add(Burger.create(this)
                        .setType(Burger.Type.ChikenBurger)
                        .setSize(iconSize, iconSize)
                        .setName("Chiken Burger")
                        .setPriceInDollars(8.99f)
                        .setDescription(getString(R.string.burger_description))
                        .build());

                customViews.add(Steak.create(this)
                        .setType(Steak.Type.Steak)
                        .setSize(iconSize, iconSize)
                        .setName("Entrecote Steak")
                        .setDescription(getString(R.string.burger_description))
                        .build());
                break;
            }
            case 2: {
                customViews.add(Fries.create(this)
                        .setType(Fries.Type.RegularFries)
                        .setSize(iconSize, iconSize)
                        .setName("Fries")
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
                break;
            }
            case 3: {
                customViews.add(ColaCan.create(this)
                        .setType(ColaCan.Type.ColaCan)
                        .setSize(iconSize, iconSize)
                        .setDescription("Description")
                        .build());

                customViews.add(SodaCan.create(this)
                        .setType(SodaCan.Type.SodaCan)
                        .setSize(iconSize, iconSize)
                        .setDescription("Description")
                        .build());

                customViews.add(ColaCan2.create(this)
                        .setType(ColaCan2.Type.ColaCan2)
                        .setSize(iconSize, iconSize)
                        .setDescription("Description")
                        .build());
                break;
            }
            case 4: {

                break;
            }
        }



        // specify an adapter (see also next example)
        FoodAdapter mAdapter = new FoodAdapter(customViews, this, ContextCompat.getColor(this, android.R.color.white));

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
        showPopUpWindow(vectorIcon, false);
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

    public void showPopUpWindow(final SagiVectorIcon vectorIcon, boolean update) {
        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom view
        View popup_window_layout = inflater.inflate(R.layout.food_details, null);

        // Food
        final SagiVectorIcon localFoodView = Globals.getFoodIcon(this, vectorIcon, 650, 850, true);
        localFoodView.setElevation(100);

        ViewGroup.LayoutParams localburgerLayoutParams = localFoodView.getLayoutParams();
        RelativeLayout.LayoutParams burgerRelativeLayoutParams =
                new RelativeLayout.LayoutParams(localburgerLayoutParams.width, localburgerLayoutParams.height);
        burgerRelativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        burgerRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        if (!(localFoodView instanceof Burger)) {
            final float scale = getResources().getDisplayMetrics().density;
            int margin_top = (int) (16 * scale + 0.5f);
            burgerRelativeLayoutParams.topMargin = margin_top;
        }

        // Add burger to customView
        ((RelativeLayout) popup_window_layout.findViewById(R.id.food_details_outer_layout)).addView(localFoodView, burgerRelativeLayoutParams);

        TextView tvPrice = (TextView) popup_window_layout.findViewById(R.id.food_details_price);
        TextView tvName = (TextView) popup_window_layout.findViewById(R.id.food_details_name);
        TextView tvDescription = (TextView) popup_window_layout.findViewById(R.id.food_details_description);
        LinearLayout extras = (LinearLayout) popup_window_layout.findViewById(R.id.food_details_extras);

        tvPrice.setText(vectorIcon.mPrice + getString(R.string.dollar_sign));
        tvName.setText(vectorIcon.mName);
        tvDescription.setText(vectorIcon.mDescription);

        TextView btnAddToCart = (TextView) popup_window_layout.findViewById(R.id.food_details_update);
        TextView btnRemoveFromCart = (TextView) popup_window_layout.findViewById(R.id.food_details_remove);

        if (update) {
            btnRemoveFromCart.setVisibility(View.VISIBLE);

            btnRemoveFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Animation anim = new ScaleAnimation(
                            0.8f, 1.05f, // Start and end values for the X axis scaling
                            0.8f, 1.05f, // Start and end values for the Y axis scaling
                            Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                            Animation.RELATIVE_TO_SELF, 0.84f); // Pivot point of Y scaling
                    anim.setFillAfter(false); // Needed to keep the result of the animation
                    anim.setDuration(300);
                    anim.setInterpolator(new BounceInterpolator());

                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (mPopupWindow != null && mPopupWindow.isShowing()) {

                                // Pass Details back to activity
                                mCartFragment.RemoveItemFromCart(localFoodView);
                                mPopupWindow.dismiss();
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    v.startAnimation(anim);
                }
            });
        }
        else {
            btnRemoveFromCart.setVisibility(View.GONE);
        }

        List<FoodExtra> extrasList = new ArrayList<>();

        for (FoodExtraState foodExtraState : localFoodView.mExtras.values()) {
            int iconSize = (screenWidth - 90) / 4;
            extrasList.add(FoodExtra.create(this).setSize(iconSize, 300).setType(foodExtraState.type).build());
        }

        // specify an adapter (see also next example)
        FoodExtraAdapter mAdapter = new FoodExtraAdapter(extrasList, this);

        extras.addView(FoodExtraRecyclerView.create(this)
                .addAdapter(mAdapter)
                .setIcon(localFoodView)
                .setNumberOfItems(4)
                .setOrientation(LinearLayoutManager.HORIZONTAL)
                .build());

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = new ScaleAnimation(
                        0.8f, 1.05f, // Start and end values for the X axis scaling
                        0.8f, 1.05f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.84f); // Pivot point of Y scaling
                anim.setFillAfter(false); // Needed to keep the result of the animation
                anim.setDuration(300);
                anim.setInterpolator(new BounceInterpolator());

                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (mPopupWindow != null && mPopupWindow.isShowing()) {
                            // Pass Details back to activity
                            mCartFragment.addItemToCart(localFoodView);
                            mPopupWindow.dismiss();
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                v.startAnimation(anim);
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
        MainActivity.this.findViewById(R.id.black_background).setVisibility(View.VISIBLE);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorDarkStatusBar));

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

//        mPopupWindow.setBackgroundDrawable(getResources().getColor(R.color.transparentBackground));
    }

    @Override
    public void onBackPressed() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();

            MainActivity.this.findViewById(R.id.black_background).setVisibility(View.GONE);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorGradientLight));
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(ArrayList<SagiVectorIcon> cart) {
        // TODO: implement
        // Proceed here to payment
        Toast.makeText(this, "Pay Cart. items: " + cart.size(), Toast.LENGTH_SHORT).show();
    }

    public void onItemClick(FoodExtra foodExtra, SagiVectorIcon icon, boolean stateAfterClick) {
        if (icon instanceof Burger) {
            Burger burger = (Burger)icon;

            switch (foodExtra.mType) {
                case Veggs: {
                    Burger b = stateAfterClick ? burger.addVegg() : burger.removeVegg();
                    break;
                }
                case Cheese: {
                    Burger b = stateAfterClick ? burger.addCheese() : burger.removeCheese();
                    break;
                }
                case Pickle: {
                }
                case Bacon: {
                }
                case Egg: {
                }
                case Chili: {
                }
                default: {
                    burger.scaleViewAnimation(0.5f, 1.0f);
                    break;
                }
            }
        }
    }
}

package com.sagihatzabi.breakingline;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout mConstraintLayout;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.content_test_layout);

        // Add burger
        View burgerView = Burger.create(this)
                .setType(Burger.Type.BeefBurger)
                .setName("Test Burger")
                .setDescription("A sandwich consisting of a bun, a cooked beef patty, and often other ingredients such as cheese, onion slices, lettuce, or condiments.")
                .setPriceInDollars(9.99f)
                .build();

        burgerView.setOnClickListener(this);

        mConstraintLayout.addView(burgerView);

    }

    @Override
    public void onClick(View v) {

        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) TestActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom view
        View popup_window_layout = inflater.inflate(R.layout.food_details, null);

        // Burger
        View localBurgerView =  Burger.create(this)
                .setType(Burger.Type.BeefBurger)
                .setSize(480, 480)
                .build();

        ViewGroup.LayoutParams localburgerLayoutParams = localBurgerView.getLayoutParams();
        RelativeLayout.LayoutParams burgerRelativeLayoutParams =
                new RelativeLayout.LayoutParams(localburgerLayoutParams.width, localburgerLayoutParams.height);
        burgerRelativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        burgerRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        final float scale = getResources().getDisplayMetrics().density;
        int margin_24 = (int) (24 * scale + 0.5f);
        burgerRelativeLayoutParams.topMargin = margin_24;

        // Add burger to customView
        ((RelativeLayout) popup_window_layout.findViewById(R.id.food_details_outer_layout)).addView(localBurgerView, burgerRelativeLayoutParams);

        TextView tvPrice = (TextView) popup_window_layout.findViewById(R.id.food_details_price);
        TextView tvName = (TextView) popup_window_layout.findViewById(R.id.food_details_name);
        TextView tvDescription = (TextView) popup_window_layout.findViewById(R.id.food_details_description);

        tvPrice.setText(((SagiVectorIcon) v).mPrice + getString(R.string.dollar_sign));
        tvName.setText(((SagiVectorIcon) v).mName);
        tvDescription.setText(((SagiVectorIcon) v).mDescription);

        TextView btnAddToCart = (TextView) popup_window_layout.findViewById(R.id.food_details_update);

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
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
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
        mPopupWindow.showAtLocation(mConstraintLayout, Gravity.CENTER,0,0);
    }


    @Override
    public void onBackPressed() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            super.onBackPressed();
        }
    }
}

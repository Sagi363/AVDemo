package com.sagihatzabi.breakingline;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;

public class OrderSummeryActivity extends AppCompatActivity {

    public static ArrayList<SagiVectorIcon> cartItems;

    private TextView mTotalPrice;
    private TextView mArriveIn;
    private TextView mBranch;
    private TextView mPayButton;

    private PopupWindow mPopupWindowArrive;
    private PopupWindow mPopupWindowBranch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summery);

        mTotalPrice = (TextView) findViewById(R.id.activity_order_summery_total_price);
        mArriveIn = (TextView) findViewById(R.id.activity_order_summery_arrive_in);
        mBranch = (TextView) findViewById(R.id.activity_order_summery_branch);
        mPayButton = (TextView) findViewById(R.id.activity_order_summery_pay);


        // Price
        float price = Globals.round(Globals.computeCartPrice(cartItems), 2);
        mTotalPrice.setText(price + getString(R.string.dollar_sign));

        // Arrive in
        mArriveIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPopupWindowArrive();
                mPopupWindowArrive.showAsDropDown(v, 0, -12);
            }
        });

        // Branch
        mBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPupWindowBranch();
                mPopupWindowBranch.showAsDropDown(v, 0, -12);
            }
        });


        // Pay
        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private RecyclerView createRecyclerView() {

        // specify an adapter (see also next example)
        FoodAdapter mAdapter = new FoodAdapter(cartItems, new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SagiVectorIcon vectorIcon) {

            }
        }, ContextCompat.getColor(this, android.R.color.black));

        // Create FoodRecyclerView
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        return recyclerView;
    }

    /**
     * show popup window method reuturn PopupWindow
     */
    private void setPopupWindowArrive() {

        // initialize a pop up window type
        mPopupWindowArrive = new PopupWindow(this);

        final ArrayList<String> arriveList = new ArrayList<String>();
        arriveList.add("15 min");
        arriveList.add("30 min");
        arriveList.add("60 min");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                arriveList);
        // the drop down list is a list view
        ListView listView = new ListView(this);

        // set our adapter and pass our pop up window contents
        listView.setAdapter(adapter);

        // set on item selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mArriveIn.setText(arriveList.get(position) + "utes");
                dismissPopup(mPopupWindowArrive);

            }
        });

        // some other visual settings for popup window
        mPopupWindowArrive.setFocusable(true);
        mPopupWindowArrive.setWidth(mArriveIn.getWidth());
        mPopupWindowArrive.setBackgroundDrawable(getResources().getDrawable(R.color.colorGradientLightWithAlpha));
        mPopupWindowArrive.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        mPopupWindowArrive.setContentView(listView);
    }

    /**
     * show popup window method reuturn PopupWindow
     */
    private void setPupWindowBranch() {

        // initialize a pop up window type
        mPopupWindowBranch = new PopupWindow(this);

        final ArrayList<String> branchList = new ArrayList<String>();
        branchList.add("Albuqueque");
        branchList.add("California");
        branchList.add("Los Angeles");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                branchList);
        // the drop down list is a list view
        ListView listView = new ListView(this);

        // set our adapter and pass our pop up window contents
        listView.setAdapter(adapter);

        // set on item selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBranch.setText(branchList.get(position));
                dismissPopup(mPopupWindowBranch);

            }
        });

        // some other visual settings for popup window
        mPopupWindowBranch.setFocusable(true);
        mPopupWindowBranch.setWidth(mBranch.getWidth());
        mPopupWindowBranch.setBackgroundDrawable(getResources().getDrawable(R.color.colorGradientLightWithAlpha));
        mPopupWindowBranch.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        mPopupWindowBranch.setContentView(listView);
    }

    private void dismissPopup(PopupWindow popupWindow) {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }



}

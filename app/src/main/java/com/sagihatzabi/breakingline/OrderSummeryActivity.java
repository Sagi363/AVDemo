package com.sagihatzabi.breakingline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;

public class OrderSummeryActivity extends AppCompatActivity {

    public static ArrayList<SagiVectorIcon> cartItems;

    private TextView mTotalPrice;
    private TextView mArriveIn;
    private TextView mBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summery);

        mTotalPrice = (TextView) findViewById(R.id.activity_order_summery_total_price);
        mArriveIn = (TextView) findViewById(R.id.activity_order_summery_arrive_in);
        mBranch = (TextView) findViewById(R.id.activity_order_summery_branch);

        float price = Globals.round(Globals.computeCartPrice(cartItems), 2);
        mTotalPrice.setText(price + getString(R.string.dollar_sign));







    }






}

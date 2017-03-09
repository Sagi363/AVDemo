package com.sagihatzabi.breakingline;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sagihatzabi.breakingline.adapters.ItemsViewAdapter;
import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;
import java.util.List;

public class OmerTestActivity extends AppCompatActivity {

    FoodRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omer_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (FoodRecyclerView) findViewById(R.id.food_recycler);
        ItemsViewAdapter adapter = new ItemsViewAdapter(getDummyList());
        recyclerView.build().addAdapter(adapter);
    }

    public List<SagiVectorIcon> getDummyList() {
        List<SagiVectorIcon> dummyList = new ArrayList<SagiVectorIcon>();

        dummyList.add(getBurger());
        dummyList.add(getBurger());
        dummyList.add(getBurger());
        dummyList.add(getBurger());
        dummyList.add(getBurger());
        dummyList.add(getBurger());
        dummyList.add(getBurger());

        return dummyList;

    }

    public SagiVectorIcon getBurger() {
        return Burger.create(this)
                .setType(Burger.Type.BeefBurger)
                .setSize(50, 50)
                .setName("Sagi Test1")
                .setPriceInDollars(9.99f)
                .removeCheese()
                .build();
    }

}

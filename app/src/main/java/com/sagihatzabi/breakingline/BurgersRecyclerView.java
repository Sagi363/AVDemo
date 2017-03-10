package com.sagihatzabi.breakingline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

public class BurgersRecyclerView extends SnappingRecyclerView {

    private int mMaxNumberOfItems = 5;
    private int mNumberOfItems;
    public Adapter mAdapter;

    public BurgersRecyclerView(Context context) {
        this(context, null);
    }

    public BurgersRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BurgersRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BurgersRecyclerView build() {

        this.enableAlphaViews(true);
        this.enableViewScaling(true);

        if (mAdapter == null) {
            // TODO: ERROR
        } else {
            this.setAdapter(mAdapter);
        }

        return this;
    }

    public BurgersRecyclerView setNumberOfItems(int numberOfItems) {
        this.mNumberOfItems = mNumberOfItems;
        return this;
    }

    public BurgersRecyclerView setOrientation(int orientation) {
        this.setLayoutManager(new LinearLayoutManager(getContext(), orientation, false));
        return this;
    }

    public BurgersRecyclerView addAdapter(Adapter adapter) {
        this.mAdapter = adapter;
        return this;
    }


    public static BurgersRecyclerView create(@NonNull final Context context) {
        return new BurgersRecyclerView(context);
    }

}
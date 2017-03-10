package com.sagihatzabi.breakingline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class FoodExtraRecyclerView extends RecyclerView {

	private int mMaxNumberOfItems = 5;
	private int mNumberOfItems;
	private Adapter mAdapter;

	public FoodExtraRecyclerView(Context context) {
		this(context, null);
	}

	public FoodExtraRecyclerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FoodExtraRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public FoodExtraRecyclerView build() {

		if (mAdapter == null) {
			// TODO: ERROR
		}
		else {
			this.setAdapter(mAdapter);
		}

		return this;
	}

	public FoodExtraRecyclerView setNumberOfItems(int numberOfItems) {
		this.mNumberOfItems = mNumberOfItems;
		return this;
	}

	public FoodExtraRecyclerView setOrientation(int orientation) {
		this.setLayoutManager(new LinearLayoutManager(getContext(), orientation, false));
		return this;
	}

	public FoodExtraRecyclerView addAdapter(Adapter adapter) {
		this.mAdapter = adapter;
		return this;
	}


	public static FoodExtraRecyclerView create(@NonNull final Context context) {
		return new FoodExtraRecyclerView(context);
	}
}
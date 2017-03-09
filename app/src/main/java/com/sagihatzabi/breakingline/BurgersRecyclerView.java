package com.sagihatzabi.breakingline;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.List;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_FLING;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;

public class BurgersRecyclerView extends RecyclerView {

	private int mMaxNumberOfItems = 5;
	private int mNumberOfItems;
	private Adapter mAdapter;

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

		if (mAdapter == null) {
			// TODO: ERROR
		}
		else {
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
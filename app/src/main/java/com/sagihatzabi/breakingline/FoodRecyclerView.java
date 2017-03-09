package com.sagihatzabi.breakingline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.Adapter;

import com.sagihatzabi.breakingline.adapters.ItemsViewAdapter;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;
import java.util.List;

public class FoodRecyclerView extends SnappingRecyclerView {

	//private boolean mStartAnimationOnCreated = true;
//	private Animation animation;
	private List<SagiVectorIcon> items;
	List<ItemsViewAdapter> adapters;
	private int maxItems;

	public FoodRecyclerView(Context context)
	{
		this(context, null);
	}

	public FoodRecyclerView(Context context, AttributeSet attrs) {

		this(context, attrs, 0);
	}

	public FoodRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.items = new ArrayList<SagiVectorIcon>();
		this.adapters = new ArrayList<ItemsViewAdapter>();
	}

	public FoodRecyclerView build() {

		return this;
	}

	public FoodRecyclerView addList(List<SagiVectorIcon> items) {
		this.items = items;
		for (ItemsViewAdapter adapter : this.adapters) {
			for (SagiVectorIcon item : items) {
				adapter.addItem(item);
			}
		}
		return this;
	}

	public FoodRecyclerView setViewScaling(boolean enabled) {
		this.enableViewScaling(enabled);
		return this;
	}

	public FoodRecyclerView setViewAlpha(boolean enabled) {
		this.enableAlphaViews(enabled);
		return this;
	}

	public FoodRecyclerView setMaxView(int maxItems) {
		this.maxItems = maxItems;
		return this;
	}

	public FoodRecyclerView addAdapter(ItemsViewAdapter adapter) {

		if (this.items.size() > 0) {
			for (SagiVectorIcon item : this.items) {
				adapter.addItem(item);
			}
		}
		this.adapters.add(adapter);
		return this;
	}


	/*	public BurgersRecyclerView addAnimation(Animation animation) {
		this.animation = animation;
		return this;
	}*/

	public static FoodRecyclerView create(@NonNull final Context context) {
		return new FoodRecyclerView(context);
	}


}
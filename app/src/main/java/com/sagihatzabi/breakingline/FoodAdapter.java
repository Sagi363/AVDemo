package com.sagihatzabi.breakingline;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.List;

/**
 * Created by sagihatzabi on 11/02/2017.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> implements OnClickListener {
    private List<SagiVectorIcon> mIcons;

    public interface OnItemClickListener {
        void onItemClick(SagiVectorIcon vectorIcon);
    }

    private final OnItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public TextView mName;
        public TextView mPrice;

        public ViewHolder(View v) {
            super(v);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.food_item_linearlayout);
            mName = (TextView) itemView.findViewById(R.id.food_item_name);
            mPrice = (TextView) itemView.findViewById(R.id.food_item_price);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FoodAdapter(List<SagiVectorIcon> mIcons, OnItemClickListener listener) {
        this.mIcons = mIcons;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        SagiVectorIcon icon = this.mIcons.get(position);

        TypedValue outValue = new TypedValue();
        icon.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true);
        icon.setBackgroundResource(outValue.resourceId);

        if(icon.getParent() != null)
            ((ViewGroup)icon.getParent()).removeView(icon); // <- fix

        if (icon.getParent() == null) holder.mLinearLayout.addView(icon, 0);
        holder.mName.setText(icon.mName);
        holder.mPrice.setText(icon.mPrice + "$");
        holder.mLinearLayout.setOnClickListener(this);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.mIcons.size();
    }

    @Override
    public void onClick(View view) {
        SagiVectorIcon icon = ((SagiVectorIcon)((LinearLayout)view).getChildAt(0));

        if(icon != null) {
            listener.onItemClick(icon);
        }

//        if (icon instanceof Burger) {
//            Burger burger = ((Burger)icon);
//        }

//        if (icon instanceof Burger && ((Burger)icon).mType == Burger.Type.ChikenBurger) {
//            Burger b = ((Burger)icon).bVegg ? ((Burger)icon).removeCheeseAndVegg() : ((Burger)icon).addCheeseAndVegg();
//        }
//        else {
//            icon.startAnimation();
//        }
    }
}

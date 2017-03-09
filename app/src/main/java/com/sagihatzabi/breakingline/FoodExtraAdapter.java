package com.sagihatzabi.breakingline;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.List;

/**
 * Created by sagihatzabi on 09/03/2017.
 */

public class FoodExtraAdapter extends RecyclerView.Adapter<FoodExtraAdapter.ViewHolder> implements View.OnClickListener {
    private List<SagiVectorIcon> mIcons;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public TextView mName;
        public TextView mPrice;

        public ViewHolder(View v) {
            super(v);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.food_extra_linearlayout);
//            mName = (TextView) itemView.findViewById(R.id.item_view_name);
//            mPrice = (TextView) itemView.findViewById(R.id.item_view_price);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FoodExtraAdapter(List<SagiVectorIcon> mIcons) {
        this.mIcons = mIcons;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FoodExtraAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_extra_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        SagiVectorIcon icon = this.mIcons.get(position);

        // Set size to width and height to WRAP_CONTENT
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                              LinearLayout.LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.CENTER_HORIZONTAL;

        TextView name = new TextView(icon.getContext());
        name.setTextSize(14);
        name.setText(icon.mName);
        name.setLayoutParams(params);

        TextView price = new TextView(icon.getContext());
        price.setTextSize(13);
        price.setText(Float.parseFloat(icon.mPrice) > 0 ?
                icon.mPrice + icon.getResources().getString(R.string.dollar_sign) : "--");
        price.setLayoutParams(params);

        Button button = new Button(icon.getContext());
        button.setTextColor(icon.getResources().getColor(android.R.color.white));
        button.setBackground(icon.getResources().getDrawable(R.drawable.minus_button));
        button.setText("-");
        button.setLayoutParams(params);

        TypedValue outValue = new TypedValue();
        icon.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true);
        icon.setBackgroundResource(outValue.resourceId);

        if(icon.getParent() != null)
            ((ViewGroup)icon.getParent()).removeView(icon); // <- fix

        if (icon.getParent() == null) holder.mLinearLayout.addView(icon, 0);
        if (name.getParent() == null) holder.mLinearLayout.addView(name, 1);
        if (price.getParent() == null) holder.mLinearLayout.addView(price, 2);
        if (button.getParent() == null) holder.mLinearLayout.addView(button, 3);
//        holder.mName.setText(icon.mName);
//        holder.mPrice.setText(icon.mPrice + "$");
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

        if (icon instanceof Burger && ((Burger)icon).mType == Burger.Type.ChikenBurger) {
            Burger b = ((Burger)icon).bVegg ? ((Burger)icon).removeCheeseAndVegg() : ((Burger)icon).addCheeseAndVegg();
        }
        else {
            icon.startAnimation();
        }
    }
}
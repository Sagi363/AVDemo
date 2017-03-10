package com.sagihatzabi.breakingline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.FoodExtra;
import com.sagihatzabi.breakingline.items.FoodExtraState;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.List;

/**
 * Created by sagihatzabi on 09/03/2017.
 */

public class FoodExtraAdapter extends RecyclerView.Adapter<FoodExtraAdapter.ViewHolder> implements View.OnClickListener {
    private List<FoodExtra> mIcons;
    SagiVectorIcon sagiVectorIcon;

    public interface OnItemClickListener {
        void onItemClick(FoodExtra foodExtra, SagiVectorIcon icon, boolean state);
    }

    private final FoodExtraAdapter.OnItemClickListener listener;

    public void setIcon(SagiVectorIcon icon) {
        this.sagiVectorIcon = icon;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public TextView mName;
        public TextView mPrice;
        public ImageButton mButton;

        public ViewHolder(View v) {
            super(v);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.food_extra_linearlayout);
            mName = (TextView) itemView.findViewById(R.id.food_extra_name);
            mPrice = (TextView) itemView.findViewById(R.id.food_extra_price);
            mButton = (ImageButton) itemView.findViewById(R.id.food_extra_button);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FoodExtraAdapter(List<FoodExtra> mIcons, OnItemClickListener listener) {
        this.mIcons = mIcons;
        this.listener = listener;
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
        FoodExtra icon = this.mIcons.get(position);
        icon.setTag(position);

//        // Set size to width and height to WRAP_CONTENT
//        LinearLayout.LayoutParams nameParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                                              LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        nameParams.gravity = Gravity.CENTER_HORIZONTAL;
//
//        DisplayMetrics displayMetrics = icon.getContext().getResources().getDisplayMetrics();
//        int margins_minus_50dp =  Math.round(50 / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
//
////        final float scale = icon.getResources().getDisplayMetrics().density;
////        int margins_minus_50dp = (int) (50 * scale + 0.5f);
//
//        nameParams.setMargins(0, -margins_minus_50dp, 0, 0);
//
//        TextView name = new TextView(icon.getContext());
//        name.setTextSize(13);
//        name.setText(icon.mName);
//        name.setLayoutParams(nameParams);
//
//        // Set size to width and height to WRAP_CONTENT
//        LinearLayout.LayoutParams priceParams =
//                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        priceParams.gravity = Gravity.CENTER_HORIZONTAL;
//
//        TextView price = new TextView(icon.getContext());
//        price.setTextSize(12);
//        price.setText(Float.parseFloat(icon.mPrice) > 0 ?
//                icon.mPrice + icon.getResources().getString(R.string.dollar_sign) : "--");
//        price.setLayoutParams(priceParams);
//
//        TextView button = new TextView(icon.getContext());
//        button.setTextColor(icon.getResources().getColor(android.R.color.white));
//        button.setBackground(icon.getResources().getDrawable(R.drawable.minus_button));
//        button.setText("-");
//        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        button.setTextSize(20);
//        button.setPadding(0, 0, 0, 0);
//        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(100, 100);
//        buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
//        buttonParams.topMargin = 12;
//        button.setLayoutParams(buttonParams);
//
//        TypedValue outValue = new TypedValue();
//        holder.mLinearLayout.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true);
//        holder.mLinearLayout.setBackgroundResource(outValue.resourceId);

        icon.setSize(icon.getWidth(), 600);

        holder.mName.setText(icon.mName);
        holder.mPrice.setText(Float.parseFloat(icon.mPrice) > 0 ?
                icon.mPrice + icon.getResources().getString(R.string.dollar_sign) : "--");

        if (sagiVectorIcon.mExtras.get(icon.mType).state) {
            holder.mButton.setBackground(icon.getResources().getDrawable(R.drawable.minus_button));
            holder.mButton.setImageResource(R.drawable.ic_remove);
        }
        else {
            holder.mButton.setBackground(icon.getResources().getDrawable(R.drawable.plus_button));
            holder.mButton.setImageResource(R.drawable.ic_add);
        }


        if(icon.getParent() != null)
            ((ViewGroup)icon.getParent()).removeView(icon); // <- fix

        if (icon.getParent() == null) holder.mLinearLayout.addView(icon, 0);
//        if (name.getParent() == null) holder.mLinearLayout.addView(name, 1);
//        if (price.getParent() == null) holder.mLinearLayout.addView(price, 2);
//        if (button.getParent() == null) holder.mLinearLayout.addView(button, 3);
//        holder.mName.setText(icon.mName);
//        holder.mPrice.setText(icon.mPrice + "$");

//        holder.mButton.setOnClickListener(this);
        holder.mLinearLayout.setOnClickListener(this);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.mIcons.size();
    }

    @Override
    public void onClick(View view) {
//        if (view instanceof ImageButton) {
//////            view.getParent().;
//            return;
//        }

        FoodExtra foodExtra = ((FoodExtra)((LinearLayout)view).getChildAt(0));

        if(foodExtra != null) {
            FoodExtraState foodExtraState = sagiVectorIcon.mExtras.get(foodExtra.mType);
            foodExtraState.state = !foodExtraState.state;
            sagiVectorIcon.mExtras.put(foodExtra.mType, foodExtraState);
            listener.onItemClick(foodExtra, this.sagiVectorIcon, foodExtraState.state);

            mIcons.get(Integer.parseInt(foodExtra.getTag().toString()));

            if (foodExtraState.state) {
                ((ImageButton)view.findViewById(R.id.food_extra_button)).setBackground(view.getResources().getDrawable(R.drawable.minus_button));
                ((ImageButton)view.findViewById(R.id.food_extra_button)).setImageResource(R.drawable.ic_remove);
            }
            else {
                ((ImageButton)view.findViewById(R.id.food_extra_button)).setBackground(view.getResources().getDrawable(R.drawable.plus_button));
                ((ImageButton)view.findViewById(R.id.food_extra_button)).setImageResource(R.drawable.ic_add);
            }

        }


//        if (view instanceof ImageButton) {
////            view.getParent().;
//            return;
//        }
//
//        SagiVectorIcon icon = ((SagiVectorIcon)((LinearLayout)view).getChildAt(0));
//
//        if (icon instanceof Burger && ((Burger)icon).mType == Burger.Type.ChikenBurger) {
//            Burger b = ((Burger)icon).bVegg ? ((Burger)icon).removeCheeseAndVegg() : ((Burger)icon).addCheeseAndVegg();
//        }
//        else {
//            icon.startAnimation();
//        }
    }
}
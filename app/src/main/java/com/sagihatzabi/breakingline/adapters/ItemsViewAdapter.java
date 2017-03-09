package com.sagihatzabi.superavd;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by sagihatzabi on 11/02/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements OnClickListener {
    private List<SagiVectorIcon> mIcons;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public TextView mName;
        public TextView mPrice;

        public ViewHolder(View v) {
            super(v);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.item_view_linearLayout);
            mName = (TextView) itemView.findViewById(R.id.item_view_name);
            mPrice = (TextView) itemView.findViewById(R.id.item_view_price);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<SagiVectorIcon> mIcons) {
        this.mIcons = mIcons;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

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

        if (icon instanceof Burger && ((Burger)icon).mType == Burger.Type.ChikenBurger) {
            Burger b = ((Burger)icon).bVegg ? ((Burger)icon).removeCheeseAndVegg() : ((Burger)icon).addCheeseAndVegg();
        }
        else {
            icon.startAnimation();
        }
    }
}

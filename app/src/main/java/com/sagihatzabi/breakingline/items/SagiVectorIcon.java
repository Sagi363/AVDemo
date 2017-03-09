package com.sagihatzabi.breakingline.items;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by sagihatzabi on 05/02/2017.
 */

@SuppressLint("AppCompatCustomView")
public class SagiVectorIcon extends ImageView {

    private static final String NO_NAME = "NO_NAME";
    private static final String NO_PRICE = "0.00";
    private static final int NO_WIDTH = -1;
    private static final int NO_HEIGHT = -1;

    public String mName = NO_NAME;
    public String mPrice = NO_PRICE;

    protected int mWidth = NO_WIDTH;
    protected int mHeight = NO_HEIGHT;
    protected boolean mStartAnimationOnCreated = true;
    protected @StyleRes    int mStyleId = 0;
    protected @DrawableRes int mDrawableId = 0;
    protected @DrawableRes int mBaseDrawableWitoutAnimId;
    protected Resources.Theme mTheme;

    private boolean active = true;
    private Drawable mDrawable;

//    private SagiVectorIcon vectorIcon;

    public SagiVectorIcon buildIcon() throws Exception {
        initNew();

        if (this.mName == NO_NAME) {
            throw new Exception("You must use setName(\"name\")");
        }
        if (this.mPrice == NO_PRICE) {
            throw new Exception("You must use setPriceInDollars(\"9.99\")");
        }
        return this;
    }

    public static SagiVectorIcon createIcon(@NonNull final Context context) {
        return new SagiVectorIcon(context);
    }

    public SagiVectorIcon setDrawableId(@DrawableRes int drawableId) {
        this.mDrawableId = drawableId;
        return this;
    }


    public SagiVectorIcon setBaseDrawableWitoutAnimationId(@DrawableRes int base_drawable_without_animation) {
        this.mBaseDrawableWitoutAnimId = base_drawable_without_animation;
        return this;
    }

    public SagiVectorIcon setStyle(@StyleRes int style) {
        this.mStyleId = style;
        return this;
    }

    public SagiVectorIcon setSize(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        return this;
    }

    public SagiVectorIcon setName(String name) {
        this.mName = name;
        return this;
    }

    public SagiVectorIcon setPriceInDollars(float price) {
        this.mPrice = "" + price;
        return this;
    }

    public SagiVectorIcon startAnimationOnCreated(boolean startAnimationOnCreated) {
        this.mStartAnimationOnCreated = startAnimationOnCreated;
        return this;
    }

    public SagiVectorIcon updateSize(int width, int height) {
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(width, height);

        this.setLayoutParams(params);

        return this;
    }

    public SagiVectorIcon(Context context, @DrawableRes int drawable, @StyleRes int style, int width, int height) {
        super(context);

        mDrawableId = drawable;
        mStyleId = style;
        init(context, width, height);
    }

    public SagiVectorIcon(Context context) {
        super(context);
    }

    public SagiVectorIcon(Context context, AttributeSet attrs) {
        super(context, attrs);

//        int[] basicAttrsArray = new int[] {
//                R.attr.srcCompat // 0
////                android.R.attr.layout_width, // 1
////                android.R.attr.layout_height // 2
//        };
//
//        TypedArray a = context.getTheme().obtainStyledAttributes(
//                attrs,
//                basicAttrsArray,
//                0, 0);
//
//        try {
//
//            mDrawable = ((VectorDrawable) a.getDrawable(0 /* index of attribute in attrsArray */));
////            int layout_width = a.getDimensionPixelSize(1, ViewGroup.LayoutParams.WRAP_CONTENT);
////            int layout_height = a.getDimensionPixelSize(2, ViewGroup.LayoutParams.WRAP_CONTENT);
//        } finally {
//            a.recycle();
//        }

        //init(context);
    }

    public SagiVectorIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initNew() {
        // Save the theme of the style
        this.mTheme = new ContextThemeWrapper(this.getContext(), this.mStyleId).getTheme();

        // Update the drawable with the style
        updateDrawableAnimation(mTheme);

        // Set background transparent
        this.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        // Set fitStyle to center
        this.setScaleType(ScaleType.CENTER_INSIDE);

        // Set size to width and height, default WRAP_CONTENT
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(mWidth != -1 ? mWidth : LinearLayout.LayoutParams.WRAP_CONTENT,
                                              mHeight != -1 ? mHeight : LinearLayout.LayoutParams.WRAP_CONTENT);

        // Weight to 1
        params.weight = 1;
        this.setLayoutParams(params);

        // Start animation
//        if (mStartAnimationOnCreated) startAnimation();
    }

    private void init(Context context, int width, int height) {
        // Save the theme of the style
        mTheme = new ContextThemeWrapper(context, mStyleId).getTheme();

        // Update the drawable with the style
        updateDrawableAnimation(mTheme);

        // Set background transparent
        this.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        // Set fitStyle to center
        this.setScaleType(ScaleType.CENTER_INSIDE);

        // Set size to width and height, default WRAP_CONTENT
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(width != -1 ? width : LinearLayout.LayoutParams.WRAP_CONTENT,
                                              height != -1 ? height : LinearLayout.LayoutParams.WRAP_CONTENT);

        // Weight to 1
        params.weight = 1;
        this.setLayoutParams(params);

        // Start animation
        if (mStartAnimationOnCreated) startAnimation();
    }

    public SagiVectorIcon(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void updateDrawableAnimation(final Resources.Theme theme) {
        this.mDrawable = ResourcesCompat.getDrawable(getResources(), mDrawableId, theme);

        if (!(this.mDrawable instanceof Animatable)) {
            this.mStartAnimationOnCreated = false;
            // TODO: REFRESH
            this.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    mBaseDrawableWitoutAnimId != 0 ? mBaseDrawableWitoutAnimId : mDrawableId, theme));
        }
        // TODO: REMOVE AFTER REFACTOR
        else {
            this.setImageDrawable(this.mDrawable);
        }

//        if (this.mDrawable != null && this.mDrawable instanceof Animatable) {
//            this.setImageDrawable(this.mDrawable);
////            ((Animatable)drawable).start();
//        }
//        else {
//            this.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
//                    mBaseDrawableWitoutAnimId != 0 ? mBaseDrawableWitoutAnimId : mDrawableId, theme));
//        }
    }



    public void scaleViewAnimation(float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.84f); // Pivot point of Y scaling
        anim.setFillAfter(false); // Needed to keep the result of the animation
        anim.setDuration(500);
//        anim.setDuration(300);
        anim.setInterpolator(new BounceInterpolator());
        this.startAnimation(anim);
//        anim = new ScaleAnimation(
//                endScale, startScale, // Start and end values for the X axis scaling
//                endScale, startScale, // Start and end values for the Y axis scaling
//                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
//                Animation.RELATIVE_TO_SELF, 0.84f); // Pivot point of Y scaling
//        anim.setFillAfter(true); // Needed to keep the result of the animation
//        anim.setDuration(260);
//        anim.setInterpolator(new BounceInterpolator());
//        this.startAnimation(anim);
    }


    public void toggleActiveStyle() {
        this.setAlpha(active ? 0.4f : 1f);
        active = !active;
    }

    public void updateStyle(@StyleRes int styleResId) {
        final Resources.Theme theme = getResources().newTheme();

        theme.applyStyle(styleResId, false);
        updateDrawableAnimation(theme);
    }

    public void updateDrawableId(@DrawableRes int drawableId) {
        this.mDrawableId = drawableId;
        updateDrawableAnimation(this.mTheme);
    }

    public void startAnimation() {
        if (mDrawable != null && mDrawable instanceof Animatable) {
            this.setImageDrawable(mDrawable);
            ((Animatable)mDrawable).start();
        }
        else {
            Log.d("SAGI", "startAnimation bug");
        }
    }

//    public SagiVectorIcon getVectorIcon() {
//        return vectorIcon;
//    }
}

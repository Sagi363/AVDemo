package com.sagihatzabi.breakingline.items;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.Log;

import com.sagihatzabi.breakingline.R;

import java.util.HashMap;

/**
 * Created by sagihatzabi on 05/02/2017.
 */

public class SodaCan extends SagiVectorIcon {

//    private @StyleableRes int[] styleableAttrs = R.styleable.ColaCanView;
    final static @DrawableRes int DRAWABLE_WITH_ANIMATION = R.drawable.ic_can;
    final static @DrawableRes int BASE_DRAWABLE_WITHOUT_ANIMATION = R.drawable.ic_can;

    final int ANIMATION_DURATION = 501;
    public Type mType;
    public boolean bSweetPotato = false;
    public boolean bHotChili = false;

    public SagiVectorIcon build() {
        this.mExtras = new HashMap<>();
//        mExtras.put(Chili, new FoodExtraState(Chili, false));

        this.setScaleX(1.25f);
        this.setScaleY(1.25f);

        try {
            this.setDrawableId(DRAWABLE_WITH_ANIMATION)
                .setBaseDrawableWitoutAnimationId(BASE_DRAWABLE_WITHOUT_ANIMATION)
                .buildIcon();
        } catch (Exception e) {
            Log.e(this.getClass().getName(), e.getMessage());
        }

        if (this.mStartAnimationOnCreated) {
            this.startAnimation();
        }

        return this;
    }

    public enum Type {
        SodaCan("Soda", 0.99f, R.style.NoStyle);

        private String stringValue;
        private float priceValueInDollars;
        private int styleValue;

        Type(String name, float price, @StyleRes int style) {
            stringValue = name;
            priceValueInDollars = price;
            styleValue = style;
        }

        String getName() {
            return stringValue;
        }

        float getPriceInDollars() {
            return priceValueInDollars;
        }

        int getStyle() {
            return styleValue;
        }

    }

    public SodaCan(Context context) {
        super(context);
    }

    public static SodaCan create(@NonNull final Context context) {
        return new SodaCan(context);
    }

    public SodaCan setType(Type type) {
        this.mType = type;
        this.setStyle(this.mType.getStyle());
        this.setName(type.getName());
        this.setPriceInDollars(type.getPriceInDollars());
        return this;
    }

    @Override
    public SodaCan setSize(int width, int height) {
        return ((SodaCan) super.setSize(width, height));
    }

    @Override
    public SodaCan setName(String name) {
        return ((SodaCan) super.setName(name));
    }

    @Override
    public SodaCan setDescription(String description) {
        return (SodaCan) super.setDescription(description);
    }

    @Override
    public SodaCan addElevation(int elevation) {
        return ((SodaCan) super.addElevation(elevation));
    }

    @Override
    public SodaCan setPriceInDollars(float price) {
        return ((SodaCan) super.setPriceInDollars(price));
    }


//    public Burger(Context context, Type burgerType) {
//        super(context, R.drawable.avd_burger_vector_anim, burgerType.getStyle(), -1, -1);
//        mType = burgerType;
//        mName = burgerType.getName();
//        mPrice = "" + burgerType.getPriceInDollars();
//
//        // Add/Remove Cheese/Vegg after the base animation
//        animHandler.postDelayed(new animRunnable(this), ANIMATION_DURATION);
//        init(null, burgerType, 0);
//    }

//    public Burger setSize(int width, int height) {
//        this.setSize(width, height);
//        return this;
//    }


//    public Burger(Context context, Type burgerType, int width, int height) {
//        super(context, R.drawable.avd_burger_vector_anim, burgerType.getStyle(), width, height);
//        mType = burgerType;
//        mName = burgerType.getName();
//        mPrice = "" + burgerType.getPriceInDollars();
//
//        // Add/Remove Cheese/Vegg after the base animation
//        animHandler.postDelayed(new animRunnable(this), ANIMATION_DURATION);
//        init(null, burgerType, 0);
//    }

//    public Burger(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(attrs, null, 0);
//    }
//
//    public Burger(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(attrs, null, defStyleAttr);
//    }
//
//    public Burger(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init(attrs, null, defStyleAttr);
//    }

//    private void init(AttributeSet attrs, Type burgerType, int defStyleRes) {
//        // Load attributes
//        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BurgerView, defStyleRes, 0);
//
//        this.mBaseDrawableWitoutAnimId = BASE_DRAWABLE_WITHOUT_ANIMATION;
////        if (burgerType != null) {
////             switch (burgerType) {
////                 case BeefBurger: {
////                    this.setStyle(R.style.BurgerColorStyle);
////                 }
////                 case ChikenBurger: {
////                     this.setStyle(R.style.ChickenBurger);
////                 }
////                 case NoVeggBurger: {
////                     this.setStyle(R.style.NoVeggBurger);
////                 }
////                 case VeggieBurger: {
////                     this.setStyle(R.style.VeggieBurger);
////                 }
////                 default: {
////
////                 }
////             }
////        }
//
//        a.recycle();
//    }

}

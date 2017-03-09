package com.sagihatzabi.breakingline.items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.annotation.StyleableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;

import com.sagihatzabi.breakingline.R;

import java.util.Queue;

/**
 * Created by sagihatzabi on 05/02/2017.
 */

public class Burger extends SagiVectorIcon {

    private @StyleableRes int[] styleableAttrs = R.styleable.BurgerView;
    final static @DrawableRes int DRAWABLE_WITH_ANIMATION = R.drawable.avd_burger_vector_anim;
    final static @DrawableRes int BASE_DRAWABLE_WITHOUT_ANIMATION = R.drawable.vd_burger_vector;

    final int ANIMATION_DURATION = 501;
    public Type mType;
    public boolean bVegg = true;
    public boolean bCheese = true;
    public boolean bSesame = true;

//    final Handler animHandler = new Handler();
    private Queue<Integer> animationDrawableQueue;

    public SagiVectorIcon build() {
        try {
            this.setDrawableId(DRAWABLE_WITH_ANIMATION)
                .setBaseDrawableWitoutAnimationId(BASE_DRAWABLE_WITHOUT_ANIMATION)
                .buildIcon();
        } catch (Exception e) {
            Log.e(this.getClass().getName(), e.getMessage());
        }

        if (this.mStartAnimationOnCreated) {
            this.startAnimation();

            // TODO: Restore animHandler here
            // Add/Remove Cheese/Vegg after the base animation
//            animHandler.postDelayed(new animRunnable(this), ANIMATION_DURATION);
        }

        return this;
    }

    public enum Type {
        BeefBurger("Cheese Burger", 5.99f, R.style.BurgerColorStyle),
        ChikenBurger("Chiken Burger", 5.99f, R.style.ChickenBurger),
        NoVeggBurger("Burger w/ Veggies", 5.99f, R.style.NoVeggBurger),
        NoCheeseBurger("Koser Burger", 5.99f, R.style.NoCheeseBurger),
        VeggieBurger("Veggie Burger", 5.99f, R.style.VeggieBurger),
        NoSesameBurger("No Sesame Burger", 5.99f, R.style.NoSesameBurger),
        NoVeggAndCheeseBurger("No Cheese & Veggies Burger", 5.99f, R.style.NoVeggAndCheeseBurger);

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

    public Burger(Context context) {
        super(context);
    }

    public static Burger create(@NonNull final Context context) {
        return new Burger(context);
    }

    public Burger setType(Type type) {
        this.mType = type;
        this.setStyle(this.mType.getStyle());
        return this;
    }

    @Override
    public Burger setSize(int width, int height) {
        return ((Burger) super.setSize(width, height));
    }

    @Override
    public Burger setName(String name) {
        return ((Burger) super.setName(name));
    }

    @Override
    public Burger setPriceInDollars(float price) {
        return ((Burger) super.setPriceInDollars(price));
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

    public void addAnimation(@DrawableRes Integer drawableId) {
        Animatable2 drawable = (Animatable2) ResourcesCompat.getDrawable(this.getResources(), drawableId, this.mTheme);
//        animationDrawableQueue.add(drawable);
    }

    public Burger removeVegg() {
        bVegg = false;
        final Resources.Theme currTheme = this.mTheme;
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_minus_vegg_vector_anim, currTheme);
        this.setImageDrawable(drawable);
        drawable.start();

//        drawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
//            @Override
//            public void onAnimationEnd(Drawable d) {
//                super.onAnimationEnd(d);
//                AnimatedVectorDrawable tempDrawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_vector_anim, currTheme);
//                updateDrawable(tempDrawable);
//            }
//        });

        return this;
    }

    public Burger addVegg() {
        bVegg = true;
        final ContextThemeWrapper wrapper = new ContextThemeWrapper(getContext(), R.style.BurgerColorStyle);
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_plus_vegg_vector_anim, wrapper.getTheme());
        this.setImageDrawable(drawable);
        drawable.start();

        return this;
    }

    public Burger removeCheese() {
        bCheese = false;
        final Resources.Theme currTheme = this.mTheme;
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_minus_cheese_vector_anim, currTheme);
        this.setImageDrawable(drawable);
        drawable.start();

//        drawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
//            @Override
//            public void onAnimationEnd(Drawable d) {
//                super.onAnimationEnd(d);
//                AnimatedVectorDrawable tempDrawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_vector_anim, currTheme);
//                updateDrawable(tempDrawable);
//            }
//        });

        return this;
    }

    public Burger addCheese() {
        bCheese = true;
        final Resources.Theme currTheme = this.mTheme;
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_plus_cheese_vector_anim, currTheme);
        this.setImageDrawable(drawable);
        drawable.start();

        return this;
    }

    public Burger removeCheeseAndVegg() {
        bCheese = false;
        bVegg = false;
        updateDrawableId(R.drawable.avd_burger_minus_vegg_and_cheese_vector_anim);

        // TODO: Check if Anim is not running, else add delay
        if (false) {
            this.startAnimation();
        }

        return this;
    }

//    public Burger removeCheeseAndVegg() {
//        bCheese = false;
//        bVegg = false;
//        final Resources.Theme currTheme = this.mTheme;
//        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_minus_vegg_and_cheese_vector_anim, currTheme);
//        this.setImageDrawable(drawable);
//        drawable.start();
//
//        return this;
//    }

    public Burger addCheeseAndVegg() {
        bCheese = true;
        bVegg = true;
        final Resources.Theme currTheme = this.mTheme;
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_plus_vegg_and_cheese_vector_anim, currTheme);
        this.setImageDrawable(drawable);
        drawable.start();

        return this;
    }
}

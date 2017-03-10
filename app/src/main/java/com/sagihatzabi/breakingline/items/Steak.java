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
import android.util.Log;

import com.sagihatzabi.breakingline.R;

import java.util.HashMap;

import static com.sagihatzabi.breakingline.items.FoodExtra.Type.Chili;

/**
 * Created by sagihatzabi on 05/02/2017.
 */

public class Steak extends SagiVectorIcon {

//    private @StyleableRes int[] styleableAttrs = R.styleable.SteakView;
    final static @DrawableRes int DRAWABLE_WITH_ANIMATION = R.drawable.avd_steak_vector_anim;
    final static @DrawableRes int BASE_DRAWABLE_WITHOUT_ANIMATION = R.drawable.avd_steak_vector_anim;

    final int ANIMATION_DURATION = 501;
    public Type mType;
    public boolean bSweetPotato = false;
    public boolean bHotChili = false;

    public SagiVectorIcon build() {
        this.mExtras = new HashMap<>();
        mExtras.put(Chili, new FoodExtraState(Chili, false));

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
        Steak("Steak", 12.99f, R.style.NoStyle);

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

    public Steak(Context context) {
        super(context);
    }

    public static Steak create(@NonNull final Context context) {
        return new Steak(context);
    }

    public Steak setType(Type type) {
        this.mType = type;
        this.setStyle(this.mType.getStyle());
        this.setName(type.getName());
        this.setPriceInDollars(type.getPriceInDollars());
        return this;
    }

    @Override
    public Steak setSize(int width, int height) {
        return ((Steak) super.setSize(width, height));
    }

    @Override
    public Steak setName(String name) {
        return ((Steak) super.setName(name));
    }

    @Override
    public Steak setDescription(String description) {
        return (Steak) super.setDescription(description);
    }

    @Override
    public Steak addElevation(int elevation) {
        return ((Steak) super.addElevation(elevation));
    }

    @Override
    public Steak setPriceInDollars(float price) {
        return ((Steak) super.setPriceInDollars(price));
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

    public Steak removeHotChili() {
        bHotChili = false;
        final Resources.Theme currTheme = this.mTheme;
//        final ContextThemeWrapper wrapper = new ContextThemeWrapper(getContext(), bHotChili ? R.style.SweetPotatoFriesStyle : R.style.FriesColorStyle);
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_minus_vegg_vector_anim, currTheme);
        this.setImageDrawable(drawable);
        drawable.start();

        return this;
    }

    public Steak addHotChili() {
        bHotChili = true;
        final Resources.Theme currTheme = this.mTheme;
//        final ContextThemeWrapper wrapper = new ContextThemeWrapper(getContext(), bHotChili ? R.style.HotSweetPotatoFriesStyle : R.style.HotFriesStyle);
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.avd_burger_plus_vegg_vector_anim, currTheme);
        this.setImageDrawable(drawable);
        drawable.start();

        return this;
    }
}

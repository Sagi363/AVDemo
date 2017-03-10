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

public class FoodExtra extends SagiVectorIcon {

    static @DrawableRes int BASE_DRAWABLE_WITHOUT_ANIMATION = R.drawable.vd_burger_vector;

    public FoodExtra.Type mType;

    public SagiVectorIcon build() {
        try {
            this.setDrawableId(BASE_DRAWABLE_WITHOUT_ANIMATION)
                .setBaseDrawableWitoutAnimationId(BASE_DRAWABLE_WITHOUT_ANIMATION)
                .buildIcon();
        } catch (Exception e) {
            Log.e(this.getClass().getName(), e.getMessage());
        }

        return this;
    }

    public enum Type {
        Veggs("Veggs", 0f, R.style.NoStyle, R.drawable.ic_veggs),
        Cheese("Cheese", 0f, R.style.NoStyle, R.drawable.ic_cheese),
        Bacon("Bacon", 0.99f, R.style.NoStyle, R.drawable.ic_bacon),
        Pickle("Pickle", 0.99f, R.style.NoStyle, R.drawable.ic_cucumber),
        Egg("Egg", 0.99f, R.style.NoStyle, R.drawable.ic_egg),
        Chili("Chili", 0.99f, R.style.NoStyle, R.drawable.ic_chili);

        private String stringValue;
        private float priceValueInDollars;
        private @StyleRes int styleValue;
        private @DrawableRes int drawableValue;

        Type(String name, float price, @StyleRes int style, @DrawableRes int drawable) {
            stringValue = name;
            priceValueInDollars = price;
            styleValue = style;
            drawableValue = drawable;
        }

        String getName() {
            return stringValue;
        }

        int getDrawable() {
            return drawableValue;
        }

        float getPriceInDollars() {
            return priceValueInDollars;
        }

        int getStyle() {
            return styleValue;
        }

    }

    public FoodExtra(Context context) {
        super(context);
    }

    public static FoodExtra create(@NonNull final Context context) {
        return new FoodExtra(context);
    }

    public FoodExtra setType(Type type) {
        this.mType = type;
        this.setStyle(this.mType.getStyle());
        BASE_DRAWABLE_WITHOUT_ANIMATION = type.getDrawable();
        this.setName(type.getName());
        this.setPriceInDollars(type.getPriceInDollars());
        return this;
    }

    @Override
    public FoodExtra setSize(int width, int height) {
        return ((FoodExtra) super.setSize(width, height));
    }

    @Override
    public FoodExtra setName(String name) {
        return ((FoodExtra) super.setName(name));
    }

    @Override
    public SagiVectorIcon setDescription(String description) {
        return super.setDescription(description);
    }

    @Override
    public FoodExtra setPriceInDollars(float price) {
        return ((FoodExtra) super.setPriceInDollars(price));
    }
}

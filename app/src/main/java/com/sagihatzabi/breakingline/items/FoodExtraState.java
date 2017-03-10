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
import android.support.v4.view.ViewCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by sagihatzabi on 05/02/2017.
 */

public class FoodExtraState {

    public FoodExtra.Type type;
    public boolean state;

    public FoodExtraState(FoodExtra.Type type, boolean state) {
        this.type = type;
        this.state = state;
    }
}

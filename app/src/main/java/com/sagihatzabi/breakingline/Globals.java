package com.sagihatzabi.breakingline;

import android.content.Context;

import com.sagihatzabi.breakingline.items.Burger;
import com.sagihatzabi.breakingline.items.ColaCan;
import com.sagihatzabi.breakingline.items.ColaCan2;
import com.sagihatzabi.breakingline.items.Fries;
import com.sagihatzabi.breakingline.items.SagiVectorIcon;
import com.sagihatzabi.breakingline.items.SodaCan;
import com.sagihatzabi.breakingline.items.Steak;

/**
 * Created by Yoav on 10-Mar-17.
 */

public class Globals {
    public static SagiVectorIcon getFoodIcon(Context context, SagiVectorIcon originalIcon, int regSize, int canSize) {
        SagiVectorIcon localFoodView = null;

        if (originalIcon instanceof Burger) {
            localFoodView = Burger.create(context)
                    .setType(((Burger) originalIcon).mType)
                    .setSize(regSize, regSize)
                    .build();
        }
        else if (originalIcon instanceof Fries) {
            localFoodView = Fries.create(context)
                    .setType(((Fries) originalIcon).mType)
                    .setSize(regSize, regSize)
                    .build();
        }
        else if (originalIcon instanceof Steak) {
            localFoodView = Steak.create(context)
                    .setType(((Steak) originalIcon).mType)
                    .setSize(regSize, regSize)
                    .build();
        }
        else if (originalIcon instanceof SodaCan) {
            localFoodView = SodaCan.create(context)
                    .setType(((SodaCan) originalIcon).mType)
                    .setSize(canSize, canSize)
                    .build();
        }
        else if (originalIcon instanceof ColaCan) {
            localFoodView = ColaCan.create(context)
                    .setType(((ColaCan) originalIcon).mType)
                    .setSize(canSize, canSize)
                    .build();
        }
        else if (originalIcon instanceof ColaCan2) {
            localFoodView = ColaCan2.create(context)
                    .setType(((ColaCan2) originalIcon).mType)
                    .setSize(canSize, canSize)
                    .build();
        }

        return localFoodView;
    }
}

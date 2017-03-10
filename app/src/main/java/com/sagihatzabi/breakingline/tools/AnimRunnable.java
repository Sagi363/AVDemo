package com.sagihatzabi.breakingline.tools;

import com.sagihatzabi.breakingline.items.Burger;

/**
 * Created by sagihatzabi on 11/02/2017.
 */

public class AnimRunnable implements Runnable {
    private Burger burger;
    private Burger.Type burgerType;

    public AnimRunnable(Burger burger) {
        this.burger = burger;
        this.burgerType = burger.mType;
    }

    @Override
    public void run() {
        switch (this.burgerType) {
            case NoVeggBurger:
                this.burger.removeVegg();
                break;
            case NoCheeseBurger:
                this.burger.removeCheese();
                break;
            case NoVeggAndCheeseBurger:
                this.burger.removeCheeseAndVegg();
                break;
            case NoSesameBurger:
                this.burger.bSesame = false;
                break;
        }
    }
}
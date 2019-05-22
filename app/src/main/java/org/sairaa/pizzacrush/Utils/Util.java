package org.sairaa.pizzacrush.Utils;

import android.support.v4.app.Fragment;

import org.sairaa.pizzacrush.Fragment.PizzaHomeFragment;
import org.sairaa.pizzacrush.Fragment.SauceAndCheeseFragment;
import org.sairaa.pizzacrush.Fragment.ToppingFragment;
import org.sairaa.pizzacrush.Model.Pizza;

public class Util {

    public static Fragment getFragment(int f){
        Fragment fragment = null;
        switch (f){
            case 1:
                fragment = new PizzaHomeFragment();
                break;
            case 2:
                fragment = new SauceAndCheeseFragment();
                break;
            case 3:
                fragment = new ToppingFragment();
                break;
            default:
                fragment = new PizzaHomeFragment();

        }
        return fragment;
    }
}

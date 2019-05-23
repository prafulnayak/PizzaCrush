package org.sairaa.pizzacrush.Utils;

import android.support.v4.app.Fragment;

import org.sairaa.pizzacrush.Fragment.PizzaHomeFragment;
import org.sairaa.pizzacrush.Fragment.SauceAndCheeseFragment;
import org.sairaa.pizzacrush.Fragment.ToppingFragment;
import org.sairaa.pizzacrush.Model.ConstantField;
import org.sairaa.pizzacrush.Model.Pizza;
import org.sairaa.pizzacrush.Model.PriceConstant;
import org.sairaa.pizzacrush.R;

public class Util implements ConstantField, PriceConstant {

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

    public static int getPizzaSizeResource(String pizzaSize) {
        int resourceId = 0;
        switch (pizzaSize){
            case Large:
                resourceId = R.drawable.large_pizza_size;
                break;
            case Medium:
                resourceId = R.drawable.medium_pizza_size;
                break;
            case Small:
                resourceId = R.drawable.small_pizza_size;
        }
        return resourceId;
    }

    public static int getSaucePizzaSize(String pizzaSize) {
        int resourceId = 0;
        switch (pizzaSize){
            case Large:
                resourceId = R.drawable.large_sauce;
                break;
            case Medium:
                resourceId = R.drawable.medium_sauce;
                break;
            case Small:
                resourceId = R.drawable.small_sauce;
        }
        return resourceId;
    }

    public static int getCheesePizzaSize(String pizzaSize) {
        int resourceId = 0;
        switch (pizzaSize){
            case Large:
                resourceId = R.drawable.large_cheese;
                break;
            case Medium:
                resourceId = R.drawable.medium_cheese;
                break;
            case Small:
                resourceId = R.drawable.minimun_cheese;
        }
        return resourceId;
    }

    public static double getPizzaSizePrice(String pizzaSize) {
        double pizzaSizePrice = 0;
        switch (pizzaSize){
            case Large:
                pizzaSizePrice = LargeSizeUnitPrice;
                break;
            case Medium:
                pizzaSizePrice = MediumSizeUnitPrice;
                break;
            case Small:
                pizzaSizePrice = SmallSizeUnitPrice;
        }
        return pizzaSizePrice;
    }

    public static double getTotalPrice(Pizza pizza) {
        double totalPrice = 0;
        totalPrice = totalPrice + Util.getPizzaSizePrice(pizza.getPizzaSize());
        totalPrice = totalPrice + (pizza.getCheese()*CheeseUnitPrice);
        totalPrice = totalPrice + (pizza.getSauce()*SauceUnitPrice);
        totalPrice = totalPrice + (pizza.getMushrum()*MushrumUnitPrice);
        totalPrice = totalPrice + (pizza.getSweetCorn()*SweetCornUnitPrice);
        totalPrice = totalPrice + (pizza.getOnion()*OnionUnitPrice);
        return totalPrice;
    }
}

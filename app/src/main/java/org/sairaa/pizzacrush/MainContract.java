package org.sairaa.pizzacrush;

import android.support.v4.app.Fragment;

import org.sairaa.pizzacrush.Model.Pizza;

//MVP implementation
public interface MainContract {

    interface View{

        void init();

        void setUpFirstUI();

        void loadFragment(Fragment fragment);

        void setUpPizzaImage(Pizza pizza);

        void setUpPizzaTopping(Pizza pizza);

        void setUpTotalPrice(Pizza pizza);

    }

    interface UserActionsListener{

        void onNextClick(int currentFragment);

        void onPreviousClick(int fragmentNo);

        void loadCorrespondingFragment();
    }
}

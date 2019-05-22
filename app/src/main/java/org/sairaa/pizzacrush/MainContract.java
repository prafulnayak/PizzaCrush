package org.sairaa.pizzacrush;

import android.support.v4.app.Fragment;

import org.sairaa.pizzacrush.Model.Pizza;

public interface MainContract {

    interface View{

        void init();

        void setUpFirstUI();

        void loadFragment(Fragment fragment);

        void setUpPizzaImage(Pizza pizza);

    }

    interface UserActionsListener{

        void onNextClick(int currentFragment);

        void onPreviousClick(int fragmentNo);

        void loadCorrespondingFragment();
    }
}

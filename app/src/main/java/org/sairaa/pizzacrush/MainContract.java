package org.sairaa.pizzacrush;

import android.support.v4.app.Fragment;

public interface MainContract {

    interface View{

        void init();

        void setUpFirstUI();

        boolean loadFragment(Fragment fragment);

    }

    interface UserActionsListener{

        void onNextClick(int currentFragment);

        void onPreviousClick();

        void loadCorrespondingFragment();
    }
}

package org.sairaa.pizzacrush;

import org.sairaa.pizzacrush.Utils.Util;

public class MainPresenter implements MainContract.UserActionsListener {

    private MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onNextClick(int currentFragment) {
        currentFragment++;
        mainView.loadFragment(Util.getFragment(currentFragment));

    }

    @Override
    public void onPreviousClick() {

    }

    @Override
    public void loadCorrespondingFragment() {

    }
}

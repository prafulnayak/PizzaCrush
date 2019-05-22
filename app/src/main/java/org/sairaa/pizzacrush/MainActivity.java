package org.sairaa.pizzacrush;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.sairaa.pizzacrush.Fragment.PizzaHomeFragment;
import org.sairaa.pizzacrush.Model.Pizza;
import org.sairaa.pizzacrush.Utils.Util;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        View.OnClickListener,
        PizzaHomeFragment.OnFragmentChangeListner {

    private static Pizza pizza;

    private int fragmentNo = 1;

    private ImageButton nextButton;
    private ImageButton previousButton;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    @Override
    public void init() {

        if(pizza== null)
            pizza = new Pizza("Awsome","Large",1,1,1,0,0,0,0);

        mainPresenter = new MainPresenter(this);

        nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(this);

        previousButton = findViewById(R.id.previous);
        previousButton.setOnClickListener(this);

        setUpFirstUI();
    }

    @Override
    public void setUpFirstUI() {

        loadFragment(Util.getFragment(fragmentNo));
    }
    @Override
    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
//                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next:
                mainPresenter.onNextClick(fragmentNo);
                fragmentNo++;
                break;
        }
    }

    @Override
    public void onFragmentChange() {

    }

    @Override
    public Pizza getPizzaDetails() {
        return pizza;
    }

    @Override
    public void setPizzaDetails(Pizza pizza) {
        MainActivity.pizza.setPizzaSize(pizza.getPizzaSize());
        Toast.makeText(this, ""+pizza.getPizzaSize(), Toast.LENGTH_SHORT).show();
    }
}

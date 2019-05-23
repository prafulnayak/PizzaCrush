package org.sairaa.pizzacrush;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.sairaa.pizzacrush.Fragment.PizzaHomeFragment;
import org.sairaa.pizzacrush.Fragment.SauceAndCheeseFragment;
import org.sairaa.pizzacrush.Fragment.ToppingFragment;
import org.sairaa.pizzacrush.Model.ConstantField;
import org.sairaa.pizzacrush.Model.Pizza;
import org.sairaa.pizzacrush.Model.PriceConstant;
import org.sairaa.pizzacrush.Utils.Util;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        View.OnClickListener, ConstantField, PriceConstant,
        PizzaHomeFragment.OnFragmentChangeListner,
        SauceAndCheeseFragment.OnSauceFragmentChangeListner,
        ToppingFragment.OntToppingFragmentChangeListner {

    private static Pizza pizza;

    private int fragmentNo = 1;

    private ImageButton nextButton;
    private ImageButton previousButton;
    private MainPresenter mainPresenter;
    private ImageView pizzaSize;
    private ImageView pizaaSauceSize;
    private ImageView pizzaCheeseSize;
    private ImageView onionImage, mushrumImage, cornImage;

    private TextView pizzaTotalPrice;

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
        pizzaSize= findViewById(R.id.pizza_size_imageView);
        pizaaSauceSize = findViewById(R.id.pizza_sauce_image);
        pizzaCheeseSize = findViewById(R.id.pizza_cheese_image);
        onionImage= findViewById(R.id.onion_image_top);
        mushrumImage = findViewById(R.id.mushrum_image_top);
        cornImage = findViewById(R.id.corn_image_top);
        pizzaTotalPrice = findViewById(R.id.pizza_total_price);


        setUpFirstUI();
    }

    @Override
    public void setUpFirstUI() {

        loadFragment(Util.getFragment(fragmentNo));
        setUpPizzaImage(pizza);
    }
    @Override
    public void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
//                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void setUpPizzaImage(Pizza pizza) {
        if(pizza.getSauce()>0){
            pizaaSauceSize.setVisibility(View.VISIBLE);
            pizaaSauceSize.setImageResource(Util.getSaucePizzaSize(pizza.getPizzaSize()));
        }else {
            pizaaSauceSize.setVisibility(View.GONE);
        }

        if(pizza.getCheese()>0){
            pizzaCheeseSize.setVisibility(View.VISIBLE);
            pizzaCheeseSize.setImageResource(Util.getCheesePizzaSize(pizza.getPizzaSize()));
        }else {
            pizzaCheeseSize.setVisibility(View.GONE);
        }
    }

    @Override
    public void setUpPizzaTopping(Pizza pizza) {
        if(pizza.getOnion()> 0){
            onionImage.setVisibility(View.VISIBLE);
        }else {
            onionImage.setVisibility(View.GONE);
        }

        if(pizza.getMushrum()>0){
            mushrumImage.setVisibility(View.VISIBLE);
        }else {
            mushrumImage.setVisibility(View.GONE);
        }

        if(pizza.getSweetCorn()>0){
            cornImage.setVisibility(View.VISIBLE);
        }else {
            cornImage.setVisibility(View.GONE);
        }

    }

    @Override
    public void setUpTotalPrice(Pizza pizza) {

        double totalPrice = 0;
        totalPrice = totalPrice + Util.getPizzaSizePrice(pizza.getPizzaSize());
        totalPrice = totalPrice + (pizza.getCheese()*CheeseUnitPrice);
        totalPrice = totalPrice + (pizza.getSauce()*SauceUnitPrice);
        totalPrice = totalPrice + (pizza.getMushrum()*MushrumUnitPrice);
        totalPrice = totalPrice + (pizza.getSweetCorn()*SweetCornUnitPrice);
        totalPrice = totalPrice + (pizza.getOnion()*OnionUnitPrice);

        pizzaTotalPrice.setText(String.valueOf(totalPrice));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next:
                mainPresenter.onNextClick(fragmentNo);
                fragmentNo++;
                break;
            case R.id.previous:
                mainPresenter.onPreviousClick(fragmentNo);
                fragmentNo--;
        }
    }

    @Override
    public void onFragmentChange() {

    }

    @Override
    public void onSauceFragmentChange() {

    }

    @Override
    public Pizza getSauceAndCheese() {
        return pizza;
    }

    @Override
    public void setSauceAndCheese(Pizza pizzaT) {
        pizza.setSauce(pizzaT.getSauce());
        pizza.setCheese(pizzaT.getCheese());
        Toast.makeText(this, ""+pizza.getSauce()+""+pizza.getCheese(), Toast.LENGTH_SHORT).show();
        setUpPizzaImage(pizza);
        setUpTotalPrice(pizza);
    }

    @Override
    public Pizza getPizzaDetails() {
        return pizza;
    }

    @Override
    public void setPizzaDetails(Pizza pizzaT) {
        pizza.setPizzaSize(pizzaT.getPizzaSize());
        Toast.makeText(this, ""+pizza.getPizzaSize(), Toast.LENGTH_SHORT).show();
        pizzaSize.setImageResource(Util.getPizzaSizeResource(pizza.getPizzaSize()));
        setUpPizzaImage(pizza);
        setUpTotalPrice(pizza);

    }

    @Override
    public void onToppingFragmentChange() {

    }

    @Override
    public Pizza getToppingDetails() {
        return pizza;
    }

    @Override
    public void setTopping(Pizza pizzaT) {
        pizza.setOnion(pizzaT.getOnion());
        pizza.setMushrum(pizzaT.getMushrum());
        pizza.setSweetCorn(pizzaT.getSweetCorn());

        setUpPizzaTopping(pizza);
        setUpTotalPrice(pizza);

    }
}

package org.sairaa.pizzacrush;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button placeOrder;
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
        // Default Pizza attributes
        if(pizza== null)
            pizza = new Pizza("Awesome",Large,0,0,0,0,0,0,0);

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
        placeOrder = findViewById(R.id.order);
        placeOrder.setOnClickListener(this);

        //setting the UI for irst time
        setUpFirstUI();
    }

    @Override
    public void setUpFirstUI() {

        loadFragment(Util.getFragment(fragmentNo));
        setUpPizzaImage(pizza);
        setUpTotalPrice(pizza);
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

    //Handle Pizza Image Size
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

    // Triggered from ToppingFragment to notice the changes
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

    //Calculate the price of the pizza according to selection
    @Override
    public void setUpTotalPrice(Pizza pizza) {

        double totalPrice = Util.getTotalPrice(pizza);
        pizzaTotalPrice.setText(String.valueOf(totalPrice));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next:
                if(fragmentNo<=2){
                    mainPresenter.onNextClick(fragmentNo);
                    fragmentNo++;
                }else {
                    Toast.makeText(this, R.string.no_screen, Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.previous:
                if(fragmentNo>=2){
                    mainPresenter.onPreviousClick(fragmentNo);
                    fragmentNo--;
                }else {
                    Toast.makeText(this, R.string.no_screen, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.order:
                double amount = Util.getTotalPrice(pizza);
                if(amount> 200){
                    Toast.makeText(this, getString(R.string.order_amount_before_text)+amount, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, R.string.unsuccess_order, Toast.LENGTH_SHORT).show();
                }

                break;




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

    // Listner from SauceAndCheese Fragment to make notice to UI
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
    // Listner from PizzaHomeFragment to make notice to UI
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

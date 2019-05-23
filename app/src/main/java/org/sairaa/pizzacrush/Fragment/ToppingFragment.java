package org.sairaa.pizzacrush.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.sairaa.pizzacrush.Model.Pizza;
import org.sairaa.pizzacrush.R;

public class ToppingFragment extends Fragment {

    private final String TAG_TOPPING = SauceAndCheeseFragment.class.getSimpleName();
    private OntToppingFragmentChangeListner changeListner;

    private Pizza pizza;

    private ImageView onionImage, cornImage, mushImage;
    private CheckBox onionCheck, cornCheck, mushCheck;
    private TextView onionPrice, cornPrice, mushPrice;
    private Button onionMinus, cornMinus, mushMinus;
    private Button onionPlus, cornPlus, mushPlus;
    private TextView onionQuantity, cornQuantity, mushQuantity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            changeListner = (OntToppingFragmentChangeListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_topping, container, false);

        onionCheck = view.findViewById(R.id.onion_checkBox);
        cornCheck = view.findViewById(R.id.corn_checkBox);
        mushCheck= view.findViewById(R.id.mushrum_checkBox);

        onionPrice = view.findViewById(R.id.onion_price);
        cornPrice = view.findViewById(R.id.corn_price);
        mushPrice = view.findViewById(R.id.mushrum_price);

        onionMinus = view.findViewById(R.id.onion_minus);
        cornMinus = view.findViewById(R.id.corn_minus);
        mushMinus = view.findViewById(R.id.mushrum_minus);

        onionPlus = view.findViewById(R.id.onion_plus);
        cornPlus = view.findViewById(R.id.corn_plus);
        mushPlus = view.findViewById(R.id.mushrum_plus);

        onionQuantity = view.findViewById(R.id.onion_quantity);
        cornQuantity = view.findViewById(R.id.corn_quantity);
        mushQuantity = view.findViewById(R.id.mushrum_quantity);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pizza = changeListner.getToppingDetails();

        if(pizza.getOnion()>0){
            onionCheck.setChecked(true);
        }else {
            onionCheck.setChecked(false);
        }

        if(pizza.getSweetCorn()>0){
            cornCheck.setChecked(true);
        }else {
            cornCheck.setChecked(false);
        }

        if(pizza.getMushrum()>0){
            mushCheck.setChecked(true);
        }else {
            mushCheck.setChecked(false);
        }

        onionCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pizza.setOnion(pizza.getOnion()+1);
                }else {
                    pizza.setOnion(0);
                }
                changeListner.setTopping(pizza);
            }
        });

        cornCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pizza.setSweetCorn(pizza.getSweetCorn()+1);
                }else {
                    pizza.setSweetCorn(0);
                }
                changeListner.setTopping(pizza);
            }
        });

        mushCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pizza.setMushrum(pizza.getMushrum()+1);
                }else {
                    pizza.setMushrum(0);
                }
                changeListner.setTopping(pizza);
            }
        });
    }

    public interface OntToppingFragmentChangeListner {
        void onToppingFragmentChange();
        Pizza getToppingDetails();
        void setTopping(Pizza pizza);
    }
}

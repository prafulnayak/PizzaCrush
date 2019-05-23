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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.sairaa.pizzacrush.Model.Pizza;
import org.sairaa.pizzacrush.Model.PriceConstant;
import org.sairaa.pizzacrush.R;

public class ToppingFragment extends Fragment implements PriceConstant {

    private final String TAG_TOPPING = SauceAndCheeseFragment.class.getSimpleName();
    //listner to activity
    private OntToppingFragmentChangeListner changeListner;

    private Pizza pizza;

    private ImageView onionImage, cornImage, mushImage;
    private CheckBox onionCheck, cornCheck, mushCheck;
    private TextView onionPrice, cornPrice, mushPrice;
    private Button onionMinus, cornMinus, mushMinus;
    private Button onionPlus, cornPlus, mushPlus;
    private TextView onionQuantity, cornQuantity, mushQuantity;
    private LinearLayout onionLayout,cornLayout, mushLayout;

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

        onionLayout = view.findViewById(R.id.onion_layout);
        cornLayout = view.findViewById(R.id.corn_layout);
        mushLayout = view.findViewById(R.id.mushrum_layout);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pizza = changeListner.getToppingDetails();

        setPrice(pizza);
        setQuantity(pizza);
        onionLayout.setVisibility(View.GONE);
        mushLayout.setVisibility(View.GONE);
        cornLayout.setVisibility(View.GONE);

        if(pizza.getOnion()>0){
            onionCheck.setChecked(true);
            onionLayout.setVisibility(View.VISIBLE);
        }else {
            onionCheck.setChecked(false);

        }

        if(pizza.getSweetCorn()>0){
            cornCheck.setChecked(true);
            cornLayout.setVisibility(View.VISIBLE);
        }else {
            cornCheck.setChecked(false);
        }

        if(pizza.getMushrum()>0){
            mushCheck.setChecked(true);
            mushLayout.setVisibility(View.VISIBLE);
        }else {
            mushCheck.setChecked(false);
        }

        onionCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pizza.setOnion(pizza.getOnion()+1);
                    onionLayout.setVisibility(View.VISIBLE);
                }else {
                    pizza.setOnion(0);
                    onionLayout.setVisibility(View.GONE);
                }
                changeListner.setTopping(pizza);
                setQuantity(pizza);
                setPrice(pizza);
            }
        });

        cornCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pizza.setSweetCorn(pizza.getSweetCorn()+1);
                    cornLayout.setVisibility(View.VISIBLE);
                }else {
                    pizza.setSweetCorn(0);
                    cornLayout.setVisibility(View.GONE);
                }
                changeListner.setTopping(pizza);
                setQuantity(pizza);
                setPrice(pizza);
            }
        });

        mushCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pizza.setMushrum(pizza.getMushrum()+1);
                    mushLayout.setVisibility(View.VISIBLE);
                }else {
                    pizza.setMushrum(0);
                    mushLayout.setVisibility(View.GONE);
                }
                changeListner.setTopping(pizza);
                setQuantity(pizza);
                setPrice(pizza);
            }
        });

        onionPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getOnion()>=2){
                    Toast.makeText(getActivity(), R.string.cannot_more_then_2_onion, Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setOnion(pizza.getOnion()+1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setTopping(pizza);
                }
            }
        });

        onionMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getOnion()<=0){
                    Toast.makeText(getActivity(), R.string.canot_lessthen_zero, Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setOnion(pizza.getOnion()- 1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setTopping(pizza);
                }
            }
        });

        cornPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getSweetCorn()>=2){
                    Toast.makeText(getActivity(), R.string.cannot_2_unit_corn, Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setSweetCorn(pizza.getSweetCorn()+1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setTopping(pizza);
                }
            }
        });

        cornMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getSweetCorn()<=0){
                    Toast.makeText(getActivity(), R.string.canot_lessthen_zero, Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setSweetCorn(pizza.getSweetCorn()- 1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setTopping(pizza);
                }
            }
        });

        mushPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getMushrum()>=2){
                    Toast.makeText(getActivity(), R.string.cannot_2_unit_mushrums, Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setMushrum(pizza.getMushrum()+1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setTopping(pizza);
                }
            }
        });

        mushMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getMushrum()<=0){
                    Toast.makeText(getActivity(), R.string.canot_lessthen_zero, Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setMushrum(pizza.getMushrum()- 1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setTopping(pizza);
                }
            }
        });
    }

    private void setQuantity(Pizza pizza) {
        if(pizza.getOnion()== 0){
            onionCheck.setChecked(false);
        }
        if(pizza.getSweetCorn() == 0){
            cornCheck.setChecked(false);
        }
        if(pizza.getMushrum() == 0){
            mushCheck.setChecked(false);
        }
        onionQuantity.setText(String.valueOf(pizza.getOnion()));
        cornQuantity.setText(String.valueOf(pizza.getSweetCorn()));
        mushQuantity.setText(String.valueOf(pizza.getMushrum()));

    }

    private void setPrice(Pizza pizza) {
        if(pizza.getOnion()== 0){
            onionPrice.setText(String.valueOf(OnionUnitPrice));
        }else {
            onionPrice.setText(String.valueOf(pizza.getOnion()*OnionUnitPrice));
        }

        if(pizza.getSweetCorn()== 0){
            cornPrice.setText(String.valueOf(SweetCornUnitPrice));
        }else {
            cornPrice.setText(String.valueOf(pizza.getSweetCorn()*SweetCornUnitPrice));
        }

        if(pizza.getMushrum()== 0){
            mushPrice.setText(String.valueOf(MushrumUnitPrice));
        }else {
            mushPrice.setText(String.valueOf(pizza.getMushrum()*MushrumUnitPrice));
        }

    }

    public interface OntToppingFragmentChangeListner {
        void onToppingFragmentChange();
        Pizza getToppingDetails();
        void setTopping(Pizza pizza);
    }
}

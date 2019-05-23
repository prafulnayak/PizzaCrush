package org.sairaa.pizzacrush.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.sairaa.pizzacrush.Model.ConstantField;
import org.sairaa.pizzacrush.Model.Pizza;
import org.sairaa.pizzacrush.Model.PriceConstant;
import org.sairaa.pizzacrush.R;
import org.sairaa.pizzacrush.Utils.Util;

public class PizzaHomeFragment extends Fragment implements ConstantField, PriceConstant {

    private Pizza pizza;
    private RadioGroup radioGroupSize;
    private RadioButton largeR, mediumR,smallR;
    private TextView largeT, mediumT, smallT;

    public PizzaHomeFragment() {
    }
    //Listner to activity
    private OnFragmentChangeListner changeListner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pizza_size, container, false);

        radioGroupSize = view.findViewById(R.id.radioGroup);

        largeR = view.findViewById(R.id.large_radio);
        mediumR = view.findViewById(R.id.medium_radio);
        smallR = view.findViewById(R.id.min_radio);

        largeT = view.findViewById(R.id.large_price);
        mediumT = view.findViewById(R.id.medium_price);
        smallT = view.findViewById(R.id.small_price);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "Pizza Home", Toast.LENGTH_SHORT).show();
//        changeListner.setPizzaDetails(pizza);

        pizza = changeListner.getPizzaDetails();

        largeT.setText(String.valueOf(Util.getPizzaSizePrice(Large)));
        mediumT.setText(String.valueOf(Util.getPizzaSizePrice(Medium)));
        smallT.setText(String.valueOf(Util.getPizzaSizePrice(Small)));

        switch (pizza.getPizzaSize()){
            case Large:
                largeR.setChecked(true);
                break;
            case Medium:
                mediumR.setChecked(true);
                break;
            case Small:
                smallR.setChecked(true);

        }

        radioGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Toast.makeText(getActivity(), ""+i+radioGroup.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.large_radio:
                        pizza.setPizzaSize(Large);
                        break;
                    case R.id.medium_radio:
                        pizza.setPizzaSize(Medium);
                        break;
                    case R.id.min_radio:
                        pizza.setPizzaSize(Small);
                        break;
                    default:
                        pizza.setPizzaSize(Large);

                }
                changeListner.setPizzaDetails(pizza);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

            changeListner = (OnFragmentChangeListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentChangeListner {
        void onFragmentChange();
        Pizza getPizzaDetails();
        void setPizzaDetails(Pizza pizza);
    }

}

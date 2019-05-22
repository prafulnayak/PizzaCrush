package org.sairaa.pizzacrush.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class SauceAndCheeseFragment extends Fragment {

    private Pizza pizza;
    private ImageView sauceImage, cheeseImage;
    private CheckBox sauceCheck, cheeseCheck;
    private TextView saucePrice, cheesePrice;
    private Button sauceMinus, cheeseMinus;
    private Button saucePlus, cheesePlus;
    private TextView sauceQuantity, cheeseQuantity;

    private final String TAG_SAUCE = SauceAndCheeseFragment.class.getSimpleName();
    private OnSauceFragmentChangeListner changeListner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG_SAUCE,"On Create");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG_SAUCE,"On Attach");
        try {

            changeListner = (OnSauceFragmentChangeListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG_SAUCE,"On CreateView");
        View view =  inflater.inflate(R.layout.fragment_sauce_cheese, container, false);
        sauceCheck = view.findViewById(R.id.sauce_checkBox);
        cheeseCheck = view.findViewById(R.id.cheese_checkbox);
        sauceQuantity = view.findViewById(R.id.sauce_quantity);
        cheeseQuantity = view.findViewById(R.id.cheese_quantity);

        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pizza = changeListner.getSauceAndCheese();
        sauceQuantity.setText(String.valueOf(pizza.getSauce()));
        cheeseQuantity.setText(String.valueOf(pizza.getCheese()));


        if(pizza.getSauce()>0){
            sauceCheck.setChecked(true);
        }else {
            sauceCheck.setChecked(false);
        }

        if(pizza.getCheese()>0){
            cheeseCheck.setChecked(true);
        }else {
            cheeseCheck.setChecked(false);
        }

        sauceCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(pizza.getSauce() == 0){
                        pizza.setSauce(1);
                        sauceQuantity.setText(String.valueOf(pizza.getSauce()));
                    }

                }else {
                    pizza.setSauce(0);
                }
                changeListner.setSauceAndCheese(pizza);

            }
        });
        cheeseCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        Log.i(TAG_SAUCE,"On Activity Created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG_SAUCE,"On Start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG_SAUCE,"On Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG_SAUCE,"On Pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG_SAUCE,"On Stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG_SAUCE,"On DestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG_SAUCE,"On Destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG_SAUCE,"On Detach");
    }

    public interface OnSauceFragmentChangeListner{
        void onSauceFragmentChange();
        Pizza getSauceAndCheese();
        void setSauceAndCheese(Pizza pizza);
    }
}

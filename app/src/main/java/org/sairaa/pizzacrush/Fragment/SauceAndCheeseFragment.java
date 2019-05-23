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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.sairaa.pizzacrush.Model.Pizza;
import org.sairaa.pizzacrush.Model.PriceConstant;
import org.sairaa.pizzacrush.R;

public class SauceAndCheeseFragment extends Fragment implements PriceConstant, View.OnClickListener {

    private Pizza pizza;
    private ImageView sauceImage, cheeseImage;
    private CheckBox sauceCheck, cheeseCheck;
    private TextView saucePrice, cheesePrice;
    private Button sauceMinus, cheeseMinus;
    private Button saucePlus, cheesePlus;
    private TextView sauceQuantity, cheeseQuantity;

    private LinearLayout sauceLayout, cheeseLayout;

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

        saucePrice = view.findViewById(R.id.sauce_unit_price);
        cheesePrice = view.findViewById(R.id.cheese_price);

        sauceMinus = view.findViewById(R.id.sauce_minus);
        cheeseMinus = view.findViewById(R.id.cheese_minus);


        saucePlus = view.findViewById(R.id.sauce_plus);
        cheesePlus = view.findViewById(R.id.cheese_plus);

        sauceLayout = view.findViewById(R.id.sauce_layout);
        cheeseLayout = view.findViewById(R.id.cheese_layout);


        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pizza = changeListner.getSauceAndCheese();

        setPrice(pizza);
        setQuantity(pizza);

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
                    pizza.setSauce(pizza.getSauce()+1);
                    sauceLayout.setVisibility(View.VISIBLE);

                }else {
                    pizza.setSauce(0);
                    sauceLayout.setVisibility(View.GONE);
                }
                changeListner.setSauceAndCheese(pizza);
                setQuantity(pizza);
                setPrice(pizza);

            }
        });
        cheeseCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pizza.setCheese(pizza.getCheese()+1);
                    cheeseLayout.setVisibility(View.VISIBLE);

                }else {
                    pizza.setCheese(0);
                    cheeseLayout.setVisibility(View.GONE);
                }
                changeListner.setSauceAndCheese(pizza);
                setQuantity(pizza);
                setPrice(pizza);
            }
        });

        saucePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getSauce()>=2){
                    Toast.makeText(getActivity(), "Cannot add more then 2 Cup of Sauce", Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setSauce(pizza.getSauce()+1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setSauceAndCheese(pizza);
                }
            }
        });

        sauceMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getSauce()<=0){
                    Toast.makeText(getActivity(), "Cannot be less then 0", Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setSauce(pizza.getSauce()- 1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setSauceAndCheese(pizza);
                }
            }
        });

        cheesePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getCheese()>=2){
                    Toast.makeText(getActivity(), "Cannot add more then 2 Cup of Cheese", Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setCheese(pizza.getCheese()+1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setSauceAndCheese(pizza);
                }
            }
        });

        cheeseMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizza.getCheese()<=0){
                    Toast.makeText(getActivity(), "Cannot be less then 0", Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setCheese(pizza.getCheese()-1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setSauceAndCheese(pizza);
                }
            }
        });


        Log.i(TAG_SAUCE,"On Activity Created");
    }

    private void setQuantity(Pizza pizza) {
        if(pizza.getSauce()== 0){
            sauceCheck.setChecked(false);
        }
        if(pizza.getCheese() == 0){
            cheeseCheck.setChecked(false);
        }
        sauceQuantity.setText(String.valueOf(pizza.getSauce()));
        cheeseQuantity.setText(String.valueOf(pizza.getCheese()));
    }

    private void setPrice(Pizza pizza) {
        if(pizza.getSauce()== 0){
            saucePrice.setText(String.valueOf(SauceUnitPrice));
        }else {
            saucePrice.setText(String.valueOf(pizza.getSauce()*SauceUnitPrice));
        }
        if(pizza.getCheese() == 0){
            cheesePrice.setText(String.valueOf(CheeseUnitPrice));
        }else {
            cheesePrice.setText(String.valueOf(pizza.getCheese()*CheeseUnitPrice));
        }




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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sauce_plus:

                break;
            case R.id.sauce_minus:

                break;
            case R.id.cheese_plus:

                break;
            case R.id.cheese_minus:
                if(pizza.getCheese()>=2){
                    Toast.makeText(getActivity(), "Cannot add more then 2 Cup of Sauce", Toast.LENGTH_SHORT).show();
                }else if(pizza.getCheese()<=0){
                    Toast.makeText(getActivity(), "Cannot be less then 0", Toast.LENGTH_SHORT).show();
                }else {
                    pizza.setCheese(pizza.getCheese()-1);
                    setQuantity(pizza);
                    setPrice(pizza);
                    changeListner.setSauceAndCheese(pizza);
                }
                break;
        }
    }

    public interface OnSauceFragmentChangeListner{
        void onSauceFragmentChange();
        Pizza getSauceAndCheese();
        void setSauceAndCheese(Pizza pizza);
    }
}

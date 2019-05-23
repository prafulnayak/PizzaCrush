package org.sairaa.pizzacrush.Model;

public class Pizza {
    private String pizzaName;
    private String pizzaSize;
    private int sauce;
    private int cheese;
    private int mushrum;
    private int onion;
    private int pepper;
    private int checken;
    private int sweetCorn;

    public Pizza() {
    }

    public Pizza(String pizzaName, String pizzaSize, int sauce, int cheese, int mushrum, int oleev, int pepper, int checken, int sausage) {
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.sauce = sauce;
        this.cheese = cheese;
        this.mushrum = mushrum;
        this.onion = oleev;
        this.pepper = pepper;
        this.checken = checken;
        this.sweetCorn = sausage;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getSauce() {
        return sauce;
    }

    public void setSauce(int sauce) {
        this.sauce = sauce;
    }

    public int getCheese() {
        return cheese;
    }

    public void setCheese(int cheese) {
        this.cheese = cheese;
    }

    public int getMushrum() {
        return mushrum;
    }

    public void setMushrum(int mushrum) {
        this.mushrum = mushrum;
    }

    public int getOnion() {
        return onion;
    }

    public void setOnion(int onion) {
        this.onion = onion;
    }

    public int getPepper() {
        return pepper;
    }

    public void setPepper(int pepper) {
        this.pepper = pepper;
    }

    public int getChecken() {
        return checken;
    }

    public void setChecken(int checken) {
        this.checken = checken;
    }

    public int getSweetCorn() {
        return sweetCorn;
    }

    public void setSweetCorn(int sweetCorn) {
        this.sweetCorn = sweetCorn;
    }
}

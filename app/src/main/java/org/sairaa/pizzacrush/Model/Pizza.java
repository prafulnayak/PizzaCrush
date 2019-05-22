package org.sairaa.pizzacrush.Model;

public class Pizza {
    private String pizzaName;
    private String pizzaSize;
    private int sauce;
    private int cheese;
    private int mushrum;
    private int oleev;
    private int pepper;
    private int checken;
    private int sausage;

    public Pizza() {
    }

    public Pizza(String pizzaName, String pizzaSize, int sauce, int cheese, int mushrum, int oleev, int pepper, int checken, int sausage) {
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.sauce = sauce;
        this.cheese = cheese;
        this.mushrum = mushrum;
        this.oleev = oleev;
        this.pepper = pepper;
        this.checken = checken;
        this.sausage = sausage;
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

    public int getOleev() {
        return oleev;
    }

    public void setOleev(int oleev) {
        this.oleev = oleev;
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

    public int getSausage() {
        return sausage;
    }

    public void setSausage(int sausage) {
        this.sausage = sausage;
    }
}

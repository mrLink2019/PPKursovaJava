package com.pp.model;

public class BeanCoffee extends Coffee {
    private BeanCoffee(String coffeeType, int priceForL, int sortsCount) {
        super(coffeeType, priceForL, sortsCount);
    }

    @Override
    public void packCoffee() {
        super.setFullVolume(getFullVolume() + 0.3);
    }

    public static Coffee buyCoffee() {
        return new BeanCoffee("Зернова кава", 350, 4);
    }

}

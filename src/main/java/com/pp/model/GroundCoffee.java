package com.pp.model;

public class GroundCoffee extends Coffee {

    private GroundCoffee(String coffeeType, int priceForL, int sortsCount) {
        super(coffeeType, priceForL, sortsCount);
    }

    @Override
    public void packCoffee() {
        super.setFullVolume(getFullVolume() + 0.2);
    }

    public static Coffee buyCoffee() {
        return new GroundCoffee("Мелена кава", 250, 6);
    }

}


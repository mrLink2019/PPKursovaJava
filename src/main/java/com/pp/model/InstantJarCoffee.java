package com.pp.model;

public class InstantJarCoffee extends Coffee {

    private InstantJarCoffee(String coffeeType, int priceForL, int sortsCount) {
        super(coffeeType, priceForL, sortsCount);
    }

    @Override
    public void packCoffee() {
        super.setFullVolume(getFullVolume() + 0.5);
    }

    public static Coffee buyCoffee() {
        return new InstantJarCoffee("Розчинна кава в банках", 200, 2);
    }

}
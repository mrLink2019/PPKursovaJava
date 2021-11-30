package com.pp.model;

public class InstantBagCoffee  extends Coffee {

    private InstantBagCoffee(String coffeeType, int priceForL, int sortsCount) {
        super(coffeeType, priceForL, sortsCount);
    }

    @Override
    public void packCoffee() {
        super.setFullVolume(getFullVolume() + 0.1);
    }

    public static Coffee buyCoffee() {
        return new InstantBagCoffee("Розчинна кава в пакетиках", 150, 5);
    }

}
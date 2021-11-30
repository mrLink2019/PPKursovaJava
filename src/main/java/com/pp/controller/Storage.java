package com.pp.controller;

import com.pp.model.Coffee;
import com.pp.view.InputScanner;

import java.io.Serializable;
import java.util.ArrayList;

public class Storage implements Serializable {
    private ArrayList<Coffee> coffeeStorage = new ArrayList<Coffee>();
    private ArrayList<Van> vansStorage = new ArrayList<Van>();
    private InputScanner scanner;
    private static Storage instance = null;

    private Storage() {
        scanner = InputScanner.getInstance();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addVan(String name, int vanVolume) {
        vansStorage.add(new Van(name, vanVolume));
    }

    public boolean deleteVan (Van selectedVan) {
        if(!vansStorage.isEmpty()) {
            return vansStorage.remove(selectedVan);
        }
        return false;
    }

    public Van chooseVan() {
        int vanId;
        vanId = scanner.getNumber();
        while (vanId <= 0 || vanId > vansStorage.size()) {
            System.out.println("Неправильний ввід");
            vanId = scanner.getNumber();
        }
        return vansStorage.get(vanId-1);
    }

    public void printVans() {
        if(!vansStorage.isEmpty()) {
            for (int i = 0; i < vansStorage.size(); i++) {
                System.out.println((i + 1) + " " + vansStorage.get(i).toString());
            }
        } else {
            System.out.println("Список пустий");
        }
    }

    public void addCoffee(Coffee newCoffee) {
        coffeeStorage.add(newCoffee);
    }

    public boolean deleteCoffee (Coffee selectedCoffee) {
        if(!coffeeStorage.isEmpty()) {
            return coffeeStorage.remove(selectedCoffee);
        }
        return false;
    }

    public Coffee chooseCoffee() {
        int coffeeId;
        coffeeId = scanner.getNumber();
        while (coffeeId <= 0 || coffeeId > coffeeStorage.size()) {
            System.out.println("Неправильний ввід");
            coffeeId = scanner.getNumber();
        }
        return coffeeStorage.get(coffeeId - 1);
    }

    public void printCoffee() {
        if(!coffeeStorage.isEmpty()) {
            for (int i = 0; i < coffeeStorage.size(); i++) {
                System.out.println((i + 1) + " " + coffeeStorage.get(i).toString());
            }
        } else {
            System.out.println("Список пустий");
        }
    }

    public void setVansStorage(ArrayList<Van> vansStorage) {
        this.vansStorage = vansStorage;
    }

    public ArrayList<Van> getVansStorage() {
        return new ArrayList<Van>(vansStorage);
    }

    public void setCoffeeStorage(ArrayList<Coffee> coffeeStorage) {
        this.coffeeStorage = coffeeStorage;
    }

    public ArrayList<Coffee> getCoffeeStorage() {
        return new ArrayList<Coffee>(coffeeStorage);
    }
}

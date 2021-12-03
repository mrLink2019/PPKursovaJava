package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;

public class SortCoffeeInVanMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.printVans();
        if(!storage.getVansStorage().isEmpty()) {
            System.out.print("\nВиберіть фургон для сортування вмісту: ");
            Van selectedVan = storage.chooseVan();
            if(!selectedVan.getCargo().isEmpty()) {
                selectedVan.sortCargo();
                System.out.println("\nВміст фургону " + selectedVan.toString() + " посортовано");
            } else {
                System.out.println("\nФургон порожній");
            }
        }
    }
}
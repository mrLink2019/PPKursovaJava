package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;

public class ViewCoffeeInVanMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.printVans();
        System.out.print("\nВиберіть фургон для перегляду вмісту: ");
        if(!storage.getVansStorage().isEmpty()) {
            Van selectedVan = storage.chooseVan();
            if(!selectedVan.getCargo().isEmpty()) {
                System.out.println("\nВміст фургону " + selectedVan);
                selectedVan.printCargo();
                System.out.println("\nЗагальний об'єм загруженої кави: " + selectedVan.getCargoVolume());
                System.out.println("Загальна ціна загруженої кави: " + selectedVan.getCargoPrice());
            } else {
                selectedVan.printCargo();
            }
        }
    }
}
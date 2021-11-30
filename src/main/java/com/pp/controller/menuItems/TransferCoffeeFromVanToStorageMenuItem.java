package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.Coffee;

public class TransferCoffeeFromVanToStorageMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.printVans();
        System.out.print("\nВиберіть фургон для переносу вмісту: ");
        Van selectedVan = storage.chooseVan();
        if(!selectedVan.getCargo().isEmpty()) {
            System.out.println("\nВміст фургону " + selectedVan);
            selectedVan.printCargo();
            System.out.print("\nВиберіть каву, яку необхідно перенести на склад: ");
            Coffee selectedCoffee = selectedVan.chooseCargo();
            selectedVan.deleteCargo(selectedCoffee);
            storage.addCoffee(selectedCoffee);
            System.out.println("\n" + selectedCoffee.toString() + " була перенесена з фургону "
                    + selectedVan + " на склад");
        } else {
            selectedVan.printCargo();
        }
    }
}
package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.Coffee;
import org.apache.log4j.Logger;

public class TransferCoffeeFromStorageToVanMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();
    private Logger logger = Logger.getLogger("generalLogger");

    @Override
    public void execute() {
        storage.printCoffee();
        if(!storage.getCoffeeStorage().isEmpty()) {
            System.out.print("\nВиберіть каву для перенесення: ");
            Coffee selectedCoffee = storage.chooseCoffee();
            storage.printVans();
            if(!storage.getVansStorage().isEmpty()) {
                System.out.print("\nВиберіть фургон для завантаження кави: ");
                Van selectedVan = storage.chooseVan();
                if(selectedVan.hasVolumeForCargo(selectedCoffee)) {
                    storage.deleteCoffee(selectedCoffee);
                    selectedVan.addCargo(selectedCoffee);
                    System.out.println(selectedCoffee.toString() + " була перенесена в фургон "
                            + selectedVan.toString());
                    logger.info(selectedCoffee.toString() + " transferred to " + selectedVan.toString());
                } else {
                    System.out.println("\nПомилка, в вибраному фургоні немає місця");
                }
            }
        } else {
            System.out.println("Склад порожній");
        }
    }
}
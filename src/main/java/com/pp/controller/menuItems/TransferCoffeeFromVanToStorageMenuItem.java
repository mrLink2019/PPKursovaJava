package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.Coffee;
import org.apache.log4j.Logger;

public class TransferCoffeeFromVanToStorageMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();
    private Logger logger = Logger.getLogger("generalLogger");

    @Override
    public void execute() {
        storage.printVans();
        if(!storage.getVansStorage().isEmpty()) {
            System.out.print("\nВиберіть фургон для переносу вмісту: ");
            Van selectedVan = storage.chooseVan();
            if (!selectedVan.getCargo().isEmpty()) {
                System.out.println("\nВміст фургону " + selectedVan);
                selectedVan.printCargo();
                System.out.print("\nВиберіть каву, яку необхідно перенести на склад: ");
                Coffee selectedCoffee = selectedVan.chooseCargo();
                selectedVan.deleteCargo(selectedCoffee);
                storage.addCoffee(selectedCoffee);
                System.out.println("\n" + selectedCoffee.toString() + " була перенесена з фургону "
                        + selectedVan + " на склад");
                logger.info(selectedCoffee.toString() + " transferred from " + selectedVan.toString());
            } else {
                System.out.println("\nФургон порожній");
            }
        }
    }
}
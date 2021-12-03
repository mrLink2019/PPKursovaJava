package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.model.Coffee;
import com.pp.view.InputScanner;
import org.apache.log4j.Logger;

public class DeleteCoffeeFromStorageMenuItem extends MenuItem {
    private InputScanner scanner = InputScanner.getInstance();
    private Storage storage = Storage.getInstance();
    private Logger logger = Logger.getLogger("generalLogger");

    @Override
    public void execute() {
        storage.printCoffee();
        if(!storage.getCoffeeStorage().isEmpty()) {
            System.out.print("\nВиберіть каву для видалення: ");
            Coffee selectedCoffee = storage.chooseCoffee();
            storage.deleteCoffee(selectedCoffee);
            System.out.println(selectedCoffee.toString() + " була видалена");
            logger.info("Van " + selectedCoffee.toString() + " deleted from storage");
        }
    }

}
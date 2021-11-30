package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.model.Coffee;
import com.pp.view.InputScanner;

public class DeleteCoffeeFromStorageMenuItem extends MenuItem {
    private InputScanner scanner = InputScanner.getInstance();
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.printCoffee();
        if(!storage.getCoffeeStorage().isEmpty()) {
            System.out.print("\nВиберіть каву для видалення: ");
            Coffee selectedCoffee = storage.chooseCoffee();
            if (storage.deleteCoffee(selectedCoffee)) {
                System.out.println(selectedCoffee.toString() + " була видалена");
            } else {
                System.out.println("Упс, щось пішло не так");
            }
        }
    }

}
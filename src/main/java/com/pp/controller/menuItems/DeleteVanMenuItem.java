package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;

public class DeleteVanMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.printVans();
        if(!storage.getVansStorage().isEmpty()) {
            System.out.print("\nВиберіть фургон для видалення: ");
            Van selectedVan = storage.chooseVan();
            if (storage.deleteVan(selectedVan)) {
                System.out.println("Фургон: " + selectedVan.toString() + " був видалений");
            } else {
                System.out.println("Упс, щось пішло не так");
            }
        }
    }
}
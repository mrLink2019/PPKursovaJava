package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import org.apache.log4j.Logger;

public class DeleteVanMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();
    private Logger logger = Logger.getLogger("generalLogger");

    @Override
    public void execute() {
        storage.printVans();
        if(!storage.getVansStorage().isEmpty()) {
            System.out.print("\nВиберіть фургон для видалення: ");
            Van selectedVan = storage.chooseVan();
            storage.deleteVan(selectedVan);
            System.out.println("Фургон: " + selectedVan.toString() + " був видалений");
            logger.info("Van " + selectedVan.toString() + " deleted from storage");
        }
    }
}
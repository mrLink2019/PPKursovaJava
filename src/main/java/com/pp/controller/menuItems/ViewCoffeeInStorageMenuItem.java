package com.pp.controller.menuItems;

import com.pp.controller.Storage;

public class ViewCoffeeInStorageMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.printCoffee();
    }
}
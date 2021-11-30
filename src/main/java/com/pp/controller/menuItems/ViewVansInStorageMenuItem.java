package com.pp.controller.menuItems;

import com.pp.controller.Storage;

public class ViewVansInStorageMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.printVans();
    }
}
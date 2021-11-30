package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.view.InputScanner;

public class CreateNewVanMenuItem extends MenuItem {
    private InputScanner scanner = InputScanner.getInstance();
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.addVan(chooseVanName(), chooseVanVolume());
    }

    private String chooseVanName() {
        System.out.print("Введіть ім'я: ");
        String vanName = scanner.getString();
        boolean duplicate = true;
        if(!storage.getVansStorage().isEmpty()) {
            while (duplicate) {
                for(int i = 0; i < storage.getVansStorage().size(); i++) {
                    if(!storage.getVansStorage().get(i).getName().equals(vanName)) {
                        duplicate = false;
                    } else {
                        System.out.print("Таке ім'я уже існує, виберіть нове: ");
                        vanName = scanner.getString();
                    }
                }

            }
        }
        return vanName;
    }

    private int chooseVanVolume() {
        System.out.print("Введіть об'єм фургону: ");
        int vanVolume = scanner.getNumber();
        while (vanVolume <= 0) {
            System.out.println("Помилка, введіть значення більше за 0");
            vanVolume = scanner.getNumber();
        }
        return vanVolume;
    }
}
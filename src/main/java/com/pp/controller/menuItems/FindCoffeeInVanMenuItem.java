package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.Coffee;
import com.pp.view.InputScanner;

import java.util.ArrayList;

public class FindCoffeeInVanMenuItem extends MenuItem {
    private InputScanner scanner = InputScanner.getInstance();
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        storage.printVans();
        if(!storage.getVansStorage().isEmpty()) {
            System.out.print("\nВиберіть фургон для пошуку вмісту: ");
            Van selectedVan = storage.chooseVan();
            if(!selectedVan.getCargo().isEmpty()) {
                System.out.print("\nВведіть найменший сорт: ");
                int minSort = scanner.getNumber();
                System.out.print("Введіть найбільший сорт: ");
                int maxSort = scanner.getNumber();
                while(minSort > maxSort) {
                    System.out.println("Помилка, введіть заново");
                    System.out.print("\nВведіть найменший сорт: ");
                    minSort = scanner.getNumber();
                    System.out.print("Введіть найбільший сорт: ");
                    maxSort = scanner.getNumber();
                }
                printFoundedCargo(selectedVan.findCargo(minSort, maxSort));
            } else {
                System.out.println("\nФургон порожній");
            }
        }
    }

    private void printFoundedCargo(ArrayList<Coffee> foundedCargo) {
        if(!foundedCargo.isEmpty()) {
            System.out.println("\nЗнайдена кава:");
            for (int i = 0; i < foundedCargo.size(); i++) {
                System.out.println((i + 1) + " " + foundedCargo.get(i).toString());
            }
        } else {
            System.out.println("Список пустий");
        }
    }
}
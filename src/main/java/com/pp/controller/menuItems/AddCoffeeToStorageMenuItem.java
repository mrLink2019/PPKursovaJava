package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.model.*;
import com.pp.view.InputScanner;

public class AddCoffeeToStorageMenuItem extends MenuItem {
    private InputScanner scanner = InputScanner.getInstance();
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        printAddCoffeeMenu();
        Coffee chosenCoffee = chooseCoffeeType();
        printCoffeeSorts(chosenCoffee);
        int coffeeSort = chooseCoffeeSort(chosenCoffee);
        int coffeePrice = chooseCoffeePrice(chosenCoffee, coffeeSort);
        chosenCoffee.setSort(coffeeSort);
        chosenCoffee.setFullPrice(coffeePrice);
        chosenCoffee.calculateFullVolume();
        chosenCoffee.packCoffee();
        storage.addCoffee(chosenCoffee);
    }

    private void printAddCoffeeMenu() {
        System.out.println("1: Зернова кава");
        System.out.println("2: Мелена кава");
        System.out.println("3: Розчинна кава в банках");
        System.out.println("4: Розчинна кава в пакетиках");;
    }

    private Coffee chooseCoffeeType() {
        int coffeeId;
        Coffee chosenCoffeeType = null;
        System.out.println("Виберіть яку саме каву ви хочете добавити: ");
        coffeeId = scanner.getNumber();
        while (coffeeId <= 0 || coffeeId > 4) {
            System.out.println("Помилка, введіть інше значення:");
            coffeeId = scanner.getNumber();
        }
        switch (coffeeId) {
            case 1:
                chosenCoffeeType = BeanCoffee.buyCoffee();
                break;
            case 2:
                chosenCoffeeType = GroundCoffee.buyCoffee();
                break;
            case 3:
                chosenCoffeeType = InstantJarCoffee.buyCoffee();
                break;
            case 4:
                chosenCoffeeType = InstantBagCoffee.buyCoffee();
                break;
        }
        return chosenCoffeeType;
    }

    private void printCoffeeSorts(Coffee coffee) {
        for(int i = 1; i <= coffee.getSortsCount(); i++) {
            System.out.println("Сорт " + i + ": ціна за 1л - " +  coffee.calculateSortPrice(i));
        }

    }

    private int chooseCoffeeSort(Coffee coffee) {
        int sortId;
        System.out.println("Виберіть сорт кави: ");
        sortId = scanner.getNumber();
        while (sortId <= 0 || sortId > coffee.getSortsCount()) {
            System.out.println("Немає такого сорту, введіть ще раз");
            sortId = scanner.getNumber();
        }
        return sortId;
    }

    private int chooseCoffeePrice(Coffee coffee, int sortId) {
        int coffeePrice;
        System.out.println("Вкажіть на скільки грошей ви хочете купити кави: ");
        coffeePrice = scanner.getNumber();
        while (coffeePrice < coffee.calculateSortPrice(sortId)) {
            System.out.println("мінімальна кількість - 1л, введіть ще раз");
            coffeePrice = scanner.getNumber();
        }
        return coffeePrice;
    }
}

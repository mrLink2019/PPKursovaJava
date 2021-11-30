package com.pp.view;

import com.pp.controller.menuItems.*;

public class UserMenu {
    private InputScanner scanner;
    private static com.pp.view.UserMenu instance = null;

    private UserMenu() {
        scanner = InputScanner.getInstance();
    }

    public static com.pp.view.UserMenu getInstance() {
        if (instance == null) {
            instance = new com.pp.view.UserMenu();
        }
        return instance;
    }

    public void startUserMenu() {
        printMainMenu();
        chooseMainMenuItem();
    }

    private void printMainMenu() {
        System.out.println("\n1: Переглянути список фургонів на складі");
        System.out.println("2: Створити новий фургон");
        System.out.println("3: Видалити фургон");
        System.out.println("4: Переглянути каву на складі");
        System.out.println("5: Купити каву на склад");
        System.out.println("6: Видалити каву зі складу");
        System.out.println("7: Перенести каву зі складу в фургон");
        System.out.println("8: Перенести каву з фургону на склад");
        System.out.println("9: Переглянути каву в фургоні");
        System.out.println("10: Посортувати каву в фургоні");
        System.out.println("11: Знайти каву певних сортів у фургоні");
        System.out.println("12: Зберегти дані в файл");
        System.out.println("13: Відновити дані з файлу");
        System.out.println("14: Вихід\n");
    }

    private void chooseMainMenuItem() {
        int menuItem;
        while(true) {
            System.out.print("Виберіть пункт меню: ");
            menuItem = scanner.getNumber();
            System.out.println();
            switch (menuItem) {
                case 1:
                    makeMenuAction(new ViewVansInStorageMenuItem());
                    startUserMenu();
                    break;
                case 2:
                    makeMenuAction(new CreateNewVanMenuItem());
                    startUserMenu();
                    break;
                case 3:
                    makeMenuAction(new DeleteVanMenuItem());
                    startUserMenu();
                    break;
                case 4:
                    makeMenuAction(new ViewCoffeeInStorageMenuItem());
                    startUserMenu();
                    break;
                case 5:
                    makeMenuAction(new AddCoffeeToStorageMenuItem());
                    startUserMenu();
                    break;
                case 6:
                    makeMenuAction(new DeleteCoffeeFromStorageMenuItem());
                    startUserMenu();
                    break;
                case 7:
                    makeMenuAction(new TransferCoffeeFromStorageToVanMenuItem());
                    startUserMenu();
                    break;
                case 8:
                    makeMenuAction(new TransferCoffeeFromVanToStorageMenuItem());
                    startUserMenu();
                    break;
                case 9:
                    makeMenuAction(new ViewCoffeeInVanMenuItem());
                    startUserMenu();
                    break;
                case 10:
                    makeMenuAction(new SortCoffeeInVanMenuItem());
                    startUserMenu();
                    break;
                case 11:
                    makeMenuAction(new FindCoffeeInVanMenuItem());
                    startUserMenu();
                    break;
                case 12:
                    makeMenuAction(new WriteToFileMenuItem());
                    startUserMenu();
                    break;
                case 13:
                    makeMenuAction(new ReadFromFileMenuItem());
                    startUserMenu();
                    break;
                case 14:
                    makeMenuAction(new ExitMenuItem());
                    break;
                default:
                    System.out.println("Неправильний ввід");
            }
        }
    }

    private void makeMenuAction(MenuItem menuItem) {
        menuItem.execute();
    }
}


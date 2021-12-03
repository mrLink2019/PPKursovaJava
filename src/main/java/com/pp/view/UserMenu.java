package com.pp.view;

import com.pp.controller.menuItems.*;
import org.apache.log4j.Logger;

public class UserMenu {
    private InputScanner scanner;
    private static UserMenu instance = null;
    private Logger logger = Logger.getLogger("generalLogger");

    private UserMenu() {
        scanner = InputScanner.getInstance();
    }

    public static UserMenu getInstance() {
        if (instance == null) {
            instance = new UserMenu();
        }
        return instance;
    }

    public void startUserMenu() {
        logger.info("UserMenu started");
        printMainMenu();
        chooseMainMenuItem();
    }

    private void printMainMenu() {
        logger.info("printMainMenu called");
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
                    logger.info("menuItem 1 chosen");
                    makeMenuAction(new ViewVansInStorageMenuItem());
                    startUserMenu();
                    break;
                case 2:
                    logger.info("menuItem 2 chosen");
                    makeMenuAction(new CreateNewVanMenuItem());
                    startUserMenu();
                    break;
                case 3:
                    logger.info("menuItem 3 chosen");
                    makeMenuAction(new DeleteVanMenuItem());
                    startUserMenu();
                    break;
                case 4:
                    logger.info("menuItem 4 chosen");
                    makeMenuAction(new ViewCoffeeInStorageMenuItem());
                    startUserMenu();
                    break;
                case 5:
                    logger.info("menuItem 5 chosen");
                    makeMenuAction(new AddCoffeeToStorageMenuItem());
                    startUserMenu();
                    break;
                case 6:
                    logger.info("menuItem 6 chosen");
                    makeMenuAction(new DeleteCoffeeFromStorageMenuItem());
                    startUserMenu();
                    break;
                case 7:
                    logger.info("menuItem 7 chosen");
                    makeMenuAction(new TransferCoffeeFromStorageToVanMenuItem());
                    startUserMenu();
                    break;
                case 8:
                    logger.info("menuItem 8 chosen");
                    makeMenuAction(new TransferCoffeeFromVanToStorageMenuItem());
                    startUserMenu();
                    break;
                case 9:
                    logger.info("menuItem 9 chosen");
                    makeMenuAction(new ViewCoffeeInVanMenuItem());
                    startUserMenu();
                    break;
                case 10:
                    logger.info("menuItem 10 chosen");
                    makeMenuAction(new SortCoffeeInVanMenuItem());
                    startUserMenu();
                    break;
                case 11:
                    logger.info("menuItem 11 chosen");
                    makeMenuAction(new FindCoffeeInVanMenuItem());
                    startUserMenu();
                    break;
                case 12:
                    logger.info("menuItem 12 chosen");
                    makeMenuAction(new WriteToFileMenuItem());
                    startUserMenu();
                    break;
                case 13:
                    logger.info("menuItem 13 chosen");
                    makeMenuAction(new ReadFromFileMenuItem());
                    startUserMenu();
                    break;
                case 14:
                    logger.info("menuItem 14 chosen");
                    makeMenuAction(new ExitMenuItem());
                    break;
                default:
                    logger.warn("invalid input in mainMenu");
                    System.out.println("Неправильний ввід");
            }
        }
    }

    private void makeMenuAction(MenuItem menuItem) {
        logger.info("makeMenuAction called with" + menuItem.getClass().getSimpleName());
        menuItem.execute();
    }
}


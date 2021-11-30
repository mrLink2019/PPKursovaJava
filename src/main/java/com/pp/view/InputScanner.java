package com.pp.view;

import java.util.Scanner;

public class InputScanner {
    private final Scanner scanner;
    private static InputScanner instance = null;

    private InputScanner() {
        scanner = new Scanner(System.in);
    }

    public int getNumber() {
        while (!scanner.hasNextInt()) {
            System.out.println("Помилка. Будь ласка введіть значення");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public String getString() {
        scanner.nextLine();
        String input = scanner.nextLine();
        while (input.isBlank()) {
            System.out.println("Помилка. Будь ласка введіть значення");
            input = scanner.nextLine();
        }
        return input;
    }

    public static synchronized InputScanner getInstance() {
        if (instance == null) {
            instance = new InputScanner();
        }
        return instance;
    }
}
package com.pp.controller.menuItems;

public class ExitMenuItem extends MenuItem {
    @Override
    public void execute() {
        System.out.println("Вихід з програми...");
        System.exit(0);
    }
}
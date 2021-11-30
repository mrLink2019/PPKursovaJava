package com.pp;

import com.pp.view.UserMenu;

public class Main {

    public static void main(String[] args) {
        UserMenu userMenu = UserMenu.getInstance();
        userMenu.startUserMenu();
    }
}

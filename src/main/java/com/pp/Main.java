package com.pp;

import com.pp.view.UserMenu;
import org.apache.log4j.Logger;

public class Main {
    private static Logger logger = Logger.getLogger("generalLogger");
    public static void main(String[] args) {
        logger.info("application has started");
        UserMenu userMenu = UserMenu.getInstance();
        userMenu.startUserMenu();
    }
}

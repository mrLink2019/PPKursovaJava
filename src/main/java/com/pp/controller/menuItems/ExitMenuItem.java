package com.pp.controller.menuItems;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class ExitMenuItem extends MenuItem {
    private Logger logger = Logger.getLogger("generalLogger");
    private Logger errorLogger = Logger.getLogger("errorLogger");

    @Override
    public void execute() {
        System.out.println("Вихід з програми...");
        logger.info("called application exit");
        System.exit(0);
    }
}
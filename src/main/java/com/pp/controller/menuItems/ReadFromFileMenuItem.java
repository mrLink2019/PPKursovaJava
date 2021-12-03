package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.Coffee;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class ReadFromFileMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();
    private Logger logger = Logger.getLogger("generalLogger");
    private Logger errorLogger = Logger.getLogger("errorLogger");

    @Override
    public void execute() {
        logger.info("trying to save Coffee data to file");
        try(ObjectInputStream ois1 = new ObjectInputStream(
                getClass().getClassLoader().getResource("savedData/coffee.dat").openStream())) {
            storage.setCoffeeStorage(((ArrayList<Coffee>)ois1.readObject()));
            ois1.close();
            System.out.println("Відновлення з файлу даних кави успішно завершено");
            logger.info("Coffee data successfully restored");
            throw new IOException("test error logger");
        } catch(Exception ex){
            errorLogger.error(ex.toString());
            System.out.println(ex.getMessage());
        }
        logger.info("trying to save Vans data to file");
        try(ObjectInputStream ois2 = new ObjectInputStream(
                getClass().getClassLoader().getResource("savedData/vans.dat").openStream())) {
            storage.setVansStorage(((ArrayList<Van>)ois2.readObject()));
            ois2.close();
            System.out.println("Відновлення з файлу даних фургонів успішно завершено");
            logger.info("Vans data successfully restored");
        } catch(Exception ex){
            errorLogger.error(ex.toString());
            System.out.println(ex.getMessage());
        }

        System.out.println("\nВідновлення з файлу завершено");
    }
}
package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import org.apache.log4j.Logger;

import java.io.*;

public class WriteToFileMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();
    private Logger logger = Logger.getLogger("generalLogger");
    private Logger errorLogger = Logger.getLogger("errorLogger");

    @Override
    public void execute() {
        logger.info("trying to save Coffee data to file");
        try(ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(
                new File(getClass().getClassLoader().getResource("savedData/coffee.dat").toURI())))) {
            oos1.writeObject(storage.getCoffeeStorage());
            oos1.close();
            System.out.println("Дані кави успішно збережені");
            logger.info("Coffee data successfully saved");
        } catch (FileNotFoundException fnfe) {
            errorLogger.error(fnfe.toString());
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            errorLogger.error(ioe.toString());
            System.out.println(ioe.getMessage());
        } catch(Exception e) {
            errorLogger.error(e.toString());
            System.out.println(e.getMessage());
        }

        logger.info("trying to save Vans data to file");
        try(ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(
                new File(getClass().getClassLoader().getResource("savedData/vans.dat").toURI())))) {
            oos2.writeObject(storage.getVansStorage());
            oos2.close();
            System.out.println("Дані фургонів успішно збережені");
            logger.info("Vans data successfully saved");
        } catch (FileNotFoundException fnfe) {
            errorLogger.error(fnfe.toString());
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            errorLogger.error(ioe.toString());
            System.out.println(ioe.getMessage());
        } catch(Exception e) {
            errorLogger.error(e.toString());
            System.out.println(e.getMessage());
        }
        System.out.println("\nЗбереження в файл завершено");
    }
}
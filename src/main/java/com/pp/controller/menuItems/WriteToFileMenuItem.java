package com.pp.controller.menuItems;

import com.pp.controller.Storage;

import java.io.*;

public class WriteToFileMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        try(ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(
                new File(getClass().getClassLoader().getResource("savedData/coffee.dat").toURI())))) {
            oos1.writeObject(storage.getCoffeeStorage());
            oos1.close();
            System.out.println("Дані кави успішно збережені");
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        try(ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(
                new File(getClass().getClassLoader().getResource("savedData/vans.dat").toURI())))) {
            oos2.writeObject(storage.getVansStorage());
            oos2.close();
            System.out.println("Дані фургонів успішно збережені");
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nЗбереження в файл завершено");
    }
}
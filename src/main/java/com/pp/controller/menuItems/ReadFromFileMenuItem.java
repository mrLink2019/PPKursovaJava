package com.pp.controller.menuItems;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.Coffee;

import java.io.*;
import java.util.ArrayList;

public class ReadFromFileMenuItem extends MenuItem {
    private Storage storage = Storage.getInstance();

    @Override
    public void execute() {
        try(ObjectInputStream ois1 = new ObjectInputStream(
                getClass().getClassLoader().getResource("savedData/coffee.dat").openStream())) {
            storage.setCoffeeStorage(((ArrayList<Coffee>)ois1.readObject()));
            ois1.close();
            System.out.println("Відновлення з файлу даних кави успішно завершено");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        try(ObjectInputStream ois2 = new ObjectInputStream(
                getClass().getClassLoader().getResource("savedData/vans.dat").openStream())) {
            storage.setVansStorage(((ArrayList<Van>)ois2.readObject()));
            ois2.close();
            System.out.println("Відновлення з файлу даних фургонів успішно завершено");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("\nВідновлення з файлу завершено");
    }
}
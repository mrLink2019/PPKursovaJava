package com.pp.model;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstantJarCoffee extends Coffee {

    private InstantJarCoffee(String coffeeType, int priceForL, int sortsCount, double packVolume) {
        super(coffeeType, priceForL, sortsCount, packVolume);
    }

    @Override
    public int calculateSortPrice(int sort) {
        return super.getPriceForL() / sort;
    }

    public static Coffee buyCoffee() {
        SQLServerDataSource dataSource1 = new SQLServerDataSource();
        dataSource1.setUser("admin");
        dataSource1.setPassword("SQLExpress");
        dataSource1.setServerName("MON");
        dataSource1.setPortNumber(1433);
        dataSource1.setDatabaseName("CoffeeShop");
        try (Connection connection = dataSource1.getConnection()){
            if(connection.isValid(1)) {
                ResultSet resultSet = connection.prepareStatement(
                        "SELECT * FROM dbo.CoffeeInfo WHERE coffeeType = 'Розчинна кава в банках'").executeQuery();
                if(resultSet.next()) {
                    String coffeeType = resultSet.getString(2);
                    int priceForL = resultSet.getInt(3);
                    int sortsCount = resultSet.getInt(4);
                    double packVolume = resultSet.getDouble(5);
                    return new InstantJarCoffee(coffeeType, priceForL, sortsCount, packVolume);
                }
            } else {
                throw new SQLException("connection isn't valid");
            }
        } catch (SQLServerException SQLEx) {
            SQLEx.printStackTrace();
        } catch (SQLException SQLEx) {
            SQLEx.printStackTrace();
        }
        return null;
    }
}
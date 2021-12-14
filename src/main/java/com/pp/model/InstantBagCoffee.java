package com.pp.model;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstantBagCoffee  extends Coffee {

    private InstantBagCoffee(String coffeeType, int priceForL, int sortsCount, double packVolume) {
        super(coffeeType, priceForL, sortsCount, packVolume);
    }

    @Override
    public int calculateSortPrice(int sort) {
        return super.getPriceForL() / sort;
    }

    public static Coffee buyCoffee() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser("admin");
        dataSource.setPassword("SQLExpress");
        dataSource.setServerName("MON");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("CoffeeShop");
        try (Connection connection = dataSource.getConnection()){
            if(connection.isValid(1)) {
                ResultSet resultSet = connection.prepareStatement(
                        "SELECT * FROM dbo.CoffeeInfo WHERE coffeeType = 'Розчинна кава в пакетиках'").executeQuery();
                if(resultSet.next()) {
                    String coffeeType = resultSet.getString(2);
                    int priceForL = resultSet.getInt(3);
                    int sortsCount = resultSet.getInt(4);
                    double packVolume = resultSet.getDouble(5);
                    return new InstantBagCoffee(coffeeType, priceForL, sortsCount, packVolume);
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
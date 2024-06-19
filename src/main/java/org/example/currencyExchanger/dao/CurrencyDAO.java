package org.example.currencyExchanger.dao;

import org.example.currencyExchanger.model.Currency;
import org.sqlite.SQLiteDataSource;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO {
    SQLiteDataSource dataSource = new SQLiteDataSource();
    URL db = getClass().getClassLoader().getResource("db.db");

    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        dataSource.setUrl("jdbc:sqlite:" + db);
        try {
            Connection connect = dataSource.getConnection();
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Currencies");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String code = rs.getString("Code");
                String name = rs.getString("FullName");
                String sing = rs.getString("Sing");
                currencies.add(new Currency(id, code, name, sing));
            }
            connect.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new ExceptionDatabase("Невозможно получить валюты из базы данных", e);
        }
        return currencies;
    }
}

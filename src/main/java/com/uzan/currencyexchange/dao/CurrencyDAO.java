package com.uzan.currencyexchange.dao;

import com.uzan.currencyexchange.db.DatabaseManager;
import com.uzan.currencyexchange.model.Currency;
import com.uzan.currencyexchange.model.Euro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO {
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        String sql = "SELECT * FROM currencies";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Currency currency = new Euro(  // пока упрощаем, позже добавим USD, RUB
                        rs.getInt("id"),
                        rs.getBoolean("available"),
                        rs.getDouble("amount"),
                        rs.getDouble("buy_rate"),
                        rs.getDouble("sell_rate")
                );
                currency.setName(rs.getString("name")); // чтобы корректно показывать имя
                currencies.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}

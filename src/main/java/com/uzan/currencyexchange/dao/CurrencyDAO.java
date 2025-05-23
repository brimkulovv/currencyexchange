package com.uzan.currencyexchange.dao;

import com.uzan.currencyexchange.db.DatabaseManager;
import com.uzan.currencyexchange.model.Currency;
import com.uzan.currencyexchange.model.Euro;

import java.sql.*;
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
                Currency currency = new Euro(
                        rs.getInt("id"),
                        rs.getBoolean("available"),
                        rs.getDouble("amount"),
                        rs.getDouble("buy_rate"),
                        rs.getDouble("sell_rate")
                );
                currency.setName(rs.getString("name"));
                currencies.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }

    public void updateCurrencyAmount(int id, double newAmount) {
        String sql = "UPDATE currencies SET amount = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newAmount);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCurrency(Currency currency) {
        String sql = "INSERT INTO currencies (name, available, amount, buy_rate, sell_rate) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, currency.getName());
            stmt.setBoolean(2, currency.isAvailable());
            stmt.setDouble(3, currency.getAmount());
            stmt.setDouble(4, currency.getBuyRate());
            stmt.setDouble(5, currency.getSellRate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

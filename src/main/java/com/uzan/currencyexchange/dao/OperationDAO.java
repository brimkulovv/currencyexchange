package com.uzan.currencyexchange.dao;

import com.uzan.currencyexchange.db.DatabaseManager;
import com.uzan.currencyexchange.model.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperationDAO {

    public void addOperation(Operation operation) {
        String sql = "INSERT INTO operations (currency_id, type, amount, date, profit) VALUES (?, ?, ?, NOW(), ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, operation.getCurrencyId());
            stmt.setString(2, operation.getType());
            stmt.setDouble(3, operation.getAmount());
            stmt.setDouble(4, operation.getProfit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

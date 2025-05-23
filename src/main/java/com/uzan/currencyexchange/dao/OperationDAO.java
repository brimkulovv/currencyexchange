package com.uzan.currencyexchange.dao;

import com.uzan.currencyexchange.db.DatabaseManager;
import com.uzan.currencyexchange.model.Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Operation> getAllOperations() {
        List<Operation> operations = new ArrayList<>();
        String sql = "SELECT * FROM operations ORDER BY date DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Operation op = new Operation();
                op.setId(rs.getInt("id"));
                op.setCurrencyId(rs.getInt("currency_id"));
                op.setType(rs.getString("type"));
                op.setAmount(rs.getDouble("amount"));
                op.setDate(rs.getTimestamp("date").toLocalDateTime());
                op.setProfit(rs.getDouble("profit"));
                operations.add(op);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operations;
    }
}

package com.uzan.currencyexchange.model;

import java.time.LocalDateTime;

public class Operation {
    private int id;
    private int currencyId;
    private String type;
    private double amount;
    private LocalDateTime date;
    private double profit;

    public Operation() {}

    public Operation(int id, int currencyId, String type, double amount, LocalDateTime date, double profit) {
        this.id = id;
        this.currencyId = currencyId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.profit = profit;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCurrencyId() { return currencyId; }
    public void setCurrencyId(int currencyId) { this.currencyId = currencyId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }
}
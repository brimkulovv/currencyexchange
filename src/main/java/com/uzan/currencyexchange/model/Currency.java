package com.uzan.currencyexchange.model;

public abstract class Currency {
    private int id;
    private String name;
    private boolean available;
    private double amount;
    private double buyRate;
    private double sellRate;

    public Currency() {}

    public Currency(int id, String name, boolean available, double amount, double buyRate, double sellRate) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.amount = amount;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    // Getters and Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getBuyRate() { return buyRate; }
    public void setBuyRate(double buyRate) { this.buyRate = buyRate; }

    public double getSellRate() { return sellRate; }
    public void setSellRate(double sellRate) { this.sellRate = sellRate; }
}

package com.uzan.currencyexchange.model;

public class Euro extends Currency {
    public Euro(int id, boolean available, double amount, double buyRate, double sellRate) {
        super(id, "Euro", available, amount, buyRate, sellRate);
    }
}


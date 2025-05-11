package com.uzan.currencyexchange.service;

import com.uzan.currencyexchange.dao.CurrencyDAO;
import com.uzan.currencyexchange.model.Currency;
import com.uzan.currencyexchange.model.Euro;

import java.util.List;

public class CurrencyService {
    private CurrencyDAO currencyDAO = new CurrencyDAO();

    public void displayAllCurrencies() {
        List<Currency> currencies = currencyDAO.getAllCurrencies();
        System.out.println("Список валют:");
        for (Currency c : currencies) {
            System.out.printf("- %s | Доступна: %s | Кол-во: %.2f | Покупка: %.2f | Продажа: %.2f\n",
                    c.getName(), c.isAvailable(), c.getAmount(), c.getBuyRate(), c.getSellRate());
        }
    }

    public void addCurrency(String name, double buyRate, double sellRate, double amount) {
        Currency currency = new Euro(0, true, amount, buyRate, sellRate);
        currency.setName(name);
        currencyDAO.addCurrency(currency);
        System.out.println("Валюта добавлена.");
    }
}


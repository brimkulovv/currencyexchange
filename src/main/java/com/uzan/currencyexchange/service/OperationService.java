package com.uzan.currencyexchange.service;

import com.uzan.currencyexchange.dao.CurrencyDAO;
import com.uzan.currencyexchange.dao.OperationDAO;
import com.uzan.currencyexchange.model.Currency;
import com.uzan.currencyexchange.model.Operation;

import java.util.List;

public class OperationService {
    private CurrencyDAO currencyDAO = new CurrencyDAO();
    private OperationDAO operationDAO = new OperationDAO();

    public void buyCurrency(String currencyName, double amount) {
        List<Currency> currencies = currencyDAO.getAllCurrencies();
        for (Currency c : currencies) {
            if (c.getName().equalsIgnoreCase(currencyName)) {
                double newAmount = c.getAmount() + amount;
                currencyDAO.updateCurrencyAmount(c.getId(), newAmount);

                Operation op = new Operation();
                op.setCurrencyId(c.getId());
                op.setType("BUY");
                op.setAmount(amount);
                op.setProfit(0);

                operationDAO.addOperation(op);
                System.out.println("Покупка завершена.");
                return;
            }
        }
        System.out.println("Валюта не найдена.");
    }

    public void sellCurrency(String currencyName, double amount) {
        List<Currency> currencies = currencyDAO.getAllCurrencies();
        for (Currency c : currencies) {
            if (c.getName().equalsIgnoreCase(currencyName)) {
                if (c.getAmount() < amount) {
                    System.out.println("Недостаточно валюты для продажи.");
                    return;
                }

                double newAmount = c.getAmount() - amount;
                currencyDAO.updateCurrencyAmount(c.getId(), newAmount);

                Operation op = new Operation();
                op.setCurrencyId(c.getId());
                op.setType("SELL");
                op.setAmount(amount);
                op.setProfit(amount * (c.getSellRate() - c.getBuyRate()));

                operationDAO.addOperation(op);
                System.out.println("Продажа завершена.");
                return;
            }
        }
        System.out.println("Валюта не найдена.");
    }
}

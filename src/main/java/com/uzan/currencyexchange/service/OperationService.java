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
                op.setProfit(0); // пока упрощённо

                operationDAO.addOperation(op);
                System.out.println("✅ Покупка завершена.");
                return;
            }
        }
        System.out.println("❌ Валюта не найдена.");
    }
}

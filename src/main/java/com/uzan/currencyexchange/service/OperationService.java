package com.uzan.currencyexchange.service;

import com.uzan.currencyexchange.dao.CurrencyDAO;
import com.uzan.currencyexchange.dao.OperationDAO;
import com.uzan.currencyexchange.model.Currency;
import com.uzan.currencyexchange.model.Operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationService {
    private final CurrencyDAO currencyDAO = new CurrencyDAO();
    private final OperationDAO operationDAO = new OperationDAO();

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
                System.out.println("Покупка завершена");
                return;
            }
        }
        System.out.println("Валюта не найдена");
    }

    public void sellCurrency(String currencyName, double amount) {
        List<Currency> currencies = currencyDAO.getAllCurrencies();
        for (Currency c : currencies) {
            if (c.getName().equalsIgnoreCase(currencyName)) {
                if (c.getAmount() < amount) {
                    System.out.println("Недостаточно средств для продажи");
                    return;
                }

                double newAmount = c.getAmount() - amount;
                double profit = amount * c.getSellRate();

                currencyDAO.updateCurrencyAmount(c.getId(), newAmount);

                Operation op = new Operation();
                op.setCurrencyId(c.getId());
                op.setType("SELL");
                op.setAmount(amount);
                op.setProfit(profit);

                operationDAO.addOperation(op);
                System.out.println("Продажа завершена");
                return;
            }
        }
        System.out.println("Валюта не найдена");
    }

    public void displayOperations() {
        List<Operation> operations = operationDAO.getAllOperations();
        List<Currency> currencies = currencyDAO.getAllCurrencies();

        if (operations.isEmpty()) {
            System.out.println("Операции не найдены");
            return;
        }
        Map<Integer, String> currencyMap = new HashMap<>();
        for (Currency currency : currencies) {
            currencyMap.put(currency.getId(), currency.getName());
        }

        System.out.println("История операций:");
        for (Operation op : operations) {
            String currencyName = currencyMap.getOrDefault(op.getCurrencyId(), "Неизвестная валюта");
            System.out.printf("- [%s] %s | Тип: %s | Сумма: %.2f | Прибыль: %.2f\n",
                    op.getDate(), currencyName, op.getType(), op.getAmount(), op.getProfit());
        }
    }
}


package com.uzan.currencyexchange;

import com.uzan.currencyexchange.service.AuthService;
import com.uzan.currencyexchange.service.CurrencyService;
import com.uzan.currencyexchange.service.OperationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск приложения Currency Exchange...");

        AuthService authService = new AuthService();
        CurrencyService currencyService = new CurrencyService();
        OperationService operationService = new OperationService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String username = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (authService.authenticate(username, password) == null) {
            System.out.println("Ошибка аутентификации. Завершение программы.");
            return;
        }

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать список валют");
            System.out.println("2. Купить валюту");
            System.out.println("3. Продать валюту");
            System.out.println("4. Добавить валюту");
            System.out.println("5. Выйти");
            System.out.print("Ваш выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    currencyService.displayAllCurrencies();
                    break;
                case 2:
                    System.out.print("Введите валюту для покупки: ");
                    String currencyNameBuy = scanner.nextLine();
                    System.out.print("Введите количество для покупки: ");
                    double amountBuy = scanner.nextDouble();
                    operationService.buyCurrency(currencyNameBuy, amountBuy);
                    break;
                case 3:
                    System.out.print("Введите валюту для продажи: ");
                    String currencyNameSell = scanner.nextLine();
                    System.out.print("Введите количество для продажи: ");
                    double amountSell = scanner.nextDouble();
                    operationService.sellCurrency(currencyNameSell, amountSell);
                    break;
                case 4:
                    System.out.print("Введите имя валюты: ");
                    String currencyNameAdd = scanner.nextLine();
                    System.out.print("Введите курс покупки: ");
                    double buyRate = scanner.nextDouble();
                    System.out.print("Введите курс продажи: ");
                    double sellRate = scanner.nextDouble();
                    System.out.print("Введите количество валюты: ");
                    double amountAdd = scanner.nextDouble();
                    currencyService.addCurrency(currencyNameAdd, buyRate, sellRate, amountAdd);
                    break;
                case 5:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}


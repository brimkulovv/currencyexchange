package com.uzan.currencyexchange;

import com.uzan.currencyexchange.service.AuthService;
import com.uzan.currencyexchange.service.CurrencyService;
import com.uzan.currencyexchange.service.OperationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск Currency Exchange...");

        AuthService authService = new AuthService();
        CurrencyService currencyService = new CurrencyService();
        OperationService operationService = new OperationService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Имя пользователя: ");
        String username = scanner.nextLine();

        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        if (!authService.authenticate(username, password)) {
            System.out.println("Программа завершена");
            return;
        }

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать валюты");
            System.out.println("2. Купить валюту");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    currencyService.displayAllCurrencies();
                    break;
                case 2:
                    System.out.print("Введите название валюты: ");
                    String currencyName = scanner.nextLine();
                    System.out.print("Введите сумму: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    operationService.buyCurrency(currencyName, amount);
                    break;
                case 3:
                    System.out.println("Выход из программы");
                    return;
                default:
                    System.out.println("Некорректный выбор");
            }
        }
    }
}

package com.uzan.currencyexchange;

import com.uzan.currencyexchange.service.AuthService;
import com.uzan.currencyexchange.service.CurrencyService;
import com.uzan.currencyexchange.service.OperationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Запуск приложения Currency Exchange...");

        AuthService authService = new AuthService();
        CurrencyService currencyService = new CurrencyService();
        OperationService operationService = new OperationService();
        Scanner scanner = new Scanner(System.in);

        // Авторизация
        System.out.print("Введите логин: ");
        String username = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (!authService.authenticate(username, password)) {
            System.out.println("Завершение программы.");
            return;
        }

        // Меню
        while (true) {
            System.out.println("\n📋 Меню:");
            System.out.println("1. Показать список валют");
            System.out.println("2. Купить валюту");
            System.out.println("3. Выйти");
            System.out.print("Ваш выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка строки

            switch (choice) {
                case 1:
                    currencyService.displayAllCurrencies();
                    break;
                case 2:
                    System.out.print("Введите валюту для покупки (например, Euro): ");
                    String currencyName = scanner.nextLine();
                    System.out.print("Введите количество для покупки: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    operationService.buyCurrency(currencyName, amount);
                    break;
                case 3:
                    System.out.println("👋 До свидания!");
                    return;
                default:
                    System.out.println("❌ Неверный выбор. Попробуйте снова.");
            }
        }
    }
}

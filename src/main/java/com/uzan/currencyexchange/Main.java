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

        if (!authService.authenticate(username, password)) {
            System.out.println("Завершение программы.");
            return;
        }

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать список валют");
            System.out.println("2. Купить валюту");
            System.out.println("3. Продать валюту");
            System.out.println("4. Показать историю операций");
            System.out.println("5. Выйти");
            System.out.print("Ваш выбор: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Введите число.");
                continue;
            }

            switch (choice) {
                case 1:
                    currencyService.displayAllCurrencies();
                    break;
                case 2:
                    System.out.print("Введите валюту для покупки: ");
                    String currencyToBuy = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    double buyAmount = readDouble(scanner);
                    operationService.buyCurrency(currencyToBuy, buyAmount);
                    break;
                case 3:
                    System.out.print("Введите валюту для продажи: ");
                    String currencyToSell = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    double sellAmount = readDouble(scanner);
                    operationService.sellCurrency(currencyToSell, sellAmount);
                    break;
                case 4:
                    operationService.displayOperations();
                    break;
                case 5:
                    System.out.println("До свидания");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Неверный ввод. Введите число: ");
            }
        }
    }
}

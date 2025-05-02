package com.uzan.currencyexchange;

import com.uzan.currencyexchange.db.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Запуск приложения Currency Exchange...");

        // Тест подключения к базе
        if (DatabaseManager.testConnection()) {
            System.out.println("✅ Подключение к базе успешно!");
        } else {
            System.out.println("❌ Не удалось подключиться к базе.");
        }
    }
}

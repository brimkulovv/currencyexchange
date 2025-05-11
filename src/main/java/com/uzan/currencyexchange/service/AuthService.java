package com.uzan.currencyexchange.service;

import com.uzan.currencyexchange.dao.UserDAO;
import com.uzan.currencyexchange.model.User;

public class AuthService {
    private UserDAO userDAO = new UserDAO();

    public boolean authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Успешный вход");
            return true;
        }
        System.out.println("Неверное имя пользователя или пароль");
        return false;
    }
}
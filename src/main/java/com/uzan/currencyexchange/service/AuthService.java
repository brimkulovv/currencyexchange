package com.uzan.currencyexchange.service;

import com.uzan.currencyexchange.dao.UserDAO;
import com.uzan.currencyexchange.model.User;

public class AuthService {
    private UserDAO userDAO = new UserDAO();

    public User authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}


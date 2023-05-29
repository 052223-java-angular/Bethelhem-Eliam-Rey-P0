package com.mycompany.app.services;

import java.io.IOException;
import java.util.Optional;

import com.mycompany.app.daos.UserDAO;
import com.mycompany.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginService {
    private UserDAO userDao;
    
    public User login(String username) throws ClassNotFoundException, IOException {
        User userOpt = userDao.findByUsername(username);
        return userOpt;
    
    
}
    
    
}

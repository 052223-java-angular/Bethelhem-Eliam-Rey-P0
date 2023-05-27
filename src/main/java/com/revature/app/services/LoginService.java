package com.revature.app.services;

import java.io.IOException;
import java.util.Optional;
import com.revature.app.daos.UserDAO;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginService {
    private UserDAO userDao;
    
    public User login(String username) throws ClassNotFoundException, IOException {
        User userOpt = userDao.findByUsername(username);
        return userOpt;
    
    
}
    
    
}

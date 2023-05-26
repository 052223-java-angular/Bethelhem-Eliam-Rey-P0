package com.revature.app.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDao;


    public User register(String username, String password) {
        
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, hashed);
        userDao.save(user);
        return user;
    }

    public boolean isValidUsername(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    

    public boolean isUniqueUsername(String username) {
         Optional<User> userOpt = userDao.findByUsername(username);

         if (userOpt.isEmpty()) {
            return true;
        }
    

       return false;
}

    public boolean isValidPassword(String password) {
         return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }


    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
     }
 }
 

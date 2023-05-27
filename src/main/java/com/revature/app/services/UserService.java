package com.revature.app.services;

import java.io.IOException;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDao;


    public User register(String username, String password) {

        String salt = BCrypt.gensalt();
        String hashedpassword = BCrypt.hashpw(password,salt);
        User user = new User(username, hashedpassword,salt);
        userDao.save(user);
        return user;
    }
    //public void login(User user) throws ClassNotFoundException, IOException{
         //userDao.findByUsername(user.getUsername());
    public User login(String username) throws ClassNotFoundException, IOException {
            Optional<User> userOpt = userDao.findByUsername(username);
            return userOpt.orElse(null);
        
        
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
 

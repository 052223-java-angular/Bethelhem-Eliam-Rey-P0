package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.UserDAO;
import com.revature.app.utils.Session;
import com.revature.app.models.User;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.LoginScreen;
import com.revature.app.screens.ProductScreen;
import com.revature.app.screens.RegisterScreen;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouterService {
  private Session session;
  private LoginService loginService;
  private User user;
    public void navigate(String path,Scanner sc) {
        switch(path){
            case "/home":
              new HomeScreen(this).start(sc);
              break;
            case "/login":
            new LoginScreen(getUserService(), this, session,loginService).start(sc);
              break;
            case "/register":
              new RegisterScreen(getUserService(), this, session).start(sc);
              break;
            case "/shopping_cart":
              break;
            case "/product":
              new ProductScreen(session).start(sc);
              break;
            case "/logout":
              session.clearSession(); // Clear the current session
              System.out.println("You have been logged out.");
              break;
            default:
                break;
        }
    }
    /*----------HELPER METHOD */
    private UserService getUserService() {
      return new UserService(new UserDAO());
  }
}

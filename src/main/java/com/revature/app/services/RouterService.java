package com.revature.app.services;

import java.util.Scanner;

import com.revature.app.daos.UserDAO;
import com.revature.app.models.Session;
import com.revature.app.screens.HomeScreen;
import com.revature.app.screens.ProductScreen;
import com.revature.app.screens.RegisterScreen;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouterService {
  private Session session;
    public void navigate(String path,Scanner sc) {
        switch(path){
            case "/home":
              new HomeScreen(this).start(sc);
              break;
            case "/login":
              break;
            case "/register":
              new RegisterScreen(getUserService(), this, session).start(sc);
              break;
            
            case "/shopping_cart":
              break;
            case "/product":
              new ProductScreen(session).start(sc);
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

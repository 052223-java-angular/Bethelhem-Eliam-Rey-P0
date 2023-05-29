package com.mycompany.app.services;

import java.util.Scanner;

import com.mycompany.app.daos.ProductDAO;
import com.mycompany.app.daos.UserDAO;
import com.mycompany.app.screens.LoginScreen;
import com.mycompany.app.screens.ProductScreen;
import com.mycompany.app.screens.HomeScreen;

public class RouterService {
  public void navigate(String path, Scanner scan) {

    switch (path) {
      case "/home":
      new HomeScreen(this).start(scan);
        break;
      case "/login":
      new LoginScreen(getLoginService(),this).start(scan);
        break;
      case "/products":
      new ProductScreen(this, getProductService()).start(scan);
        break;
      case "/registration":
        break;
      case "/logout":
        clearScreen();
        System.out.println("You have been logged out.");  
        break;
      default:
        break;
    }
  }

  private ProductService getProductService() {
    return new ProductService(new ProductDAO());
  }
  private LoginService getLoginService() {
    return new LoginService(new UserDAO());
}
  private void clearScreen(){
    System.out.println("\033[H\033[2J");
    System.out.flush();
}
}

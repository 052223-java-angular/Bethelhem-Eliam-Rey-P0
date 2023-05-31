package com.mycompany.app.screens;

import java.util.List;
import java.util.Scanner;

import com.mycompany.app.models.Product;
import com.mycompany.app.services.ProductService;
import com.mycompany.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductScreen implements IScreen {
  private final RouterService router;
  private final ProductService productService;

  @Override
  public void start(Scanner scan) {
    String input = "";
    String username = "";
    exit:
    {
      while (true) {
        clearScreen();
        System.out.println("Welcome to the Product Screen");
        username = scan.toString();

        System.out.println("\n[1] Display all products");
        System.out.println("[2] Search for specific product");
        System.out.println("[x] Exit");
        System.out.print("Enter your choice: ");
        System.out.println();

        input=scan.nextLine(); 
        
        switch(input.toLowerCase()){ 
          case "1":
            List<Product> prod = productService.getAllProducts();
            if (prod.size() == 0) {
              System.out.println("No products to display");
            } else {
              for (int i = 0; i < prod.size(); i++) {
                System.out.println(prod.get(i).toString());
              }
            }
            break ;
          case "2":
            clearScreen(); 
            router.navigate("/searchproduct", scan);
            break;
          case "x":
             clearScreen(); 
             router.navigate("/home", scan);
             break;
          default:
            clearScreen();
            System.out.println("Invalid option!");
            System.out.print("\nPress enter to continue...");
            scan.nextLine();
            break;
        }
      }
    }
  }

private void clearScreen(){
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }
}

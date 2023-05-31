package com.mycompany.app.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.models.Product;
import com.mycompany.app.services.ProductService;


import lombok.AllArgsConstructor;
@AllArgsConstructor
public class SearchingBarScreen {
    private static final Logger logger = LogManager.getLogger(HomeScreen.class);
    private final ProductService productService;
    

    public String getProductName(Scanner sc) {
        logger.info("--- NAVIGATED TO SEARCH BAR----");
        System.out.println("PLEASE ENTER WHAT YOU ARE LOOKING FOR");
        return sc.nextLine();
    }

    public void searchProduct(Scanner sc) {
        do{
        String productName = getProductName(sc);
        Product product = productService.getProduct(productName);
        if (product != null) {
            System.out.println("Product found:");
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
        } else {
            System.out.println("Product not found.");
            System.out.println("Do you want to continue your searching? \n[y] [n]");
            String choice = sc.nextLine().toLowerCase();
            if(!choice.equals("y"))
            break;
        }
    }while(true);
}
}



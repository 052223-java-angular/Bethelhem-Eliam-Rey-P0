package com.revature.app.screens;

import java.util.Scanner;


import com.revature.app.services.RouterService;

public class HomeScreen implements IScreen{
    private final RouterService router;
    
    public HomeScreen(RouterService router){
        this.router=router;
    }

    @Override
    public void start(Scanner sc) {
       String input ="";

       exit:{
         while(true){
            clearScreen();
            System.out.println("WELCOME TO E-SHOPPING!");
            System.out.println("\n[1] Login Screen");
            System.out.println("[2] Registration Screen");
            System.out.println("[x] Exit");

            System.out.println("\nEnter: ");
            input=sc.nextLine();

            switch(input.toLowerCase()){
                case "1":
                  break;
                case "2":
                  router.navigate("/register", sc);
                  break;
                case "x":
                System.out.println("\nGoodbye!");
                  break exit;
                default:
                  clearScreen();
                  System.out.println("\nInvalid input");
                  System.out.println("\n press enter to continue...");
                  sc.nextLine();
                  break;
            }

         }
       }
      

    }
    /*-----------------------HELPER METHOD---------------- */
    private void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}

package com.revature.app.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.app.utils.Session;
import com.revature.app.models.User;
import com.revature.app.services.RouterService;
import com.revature.app.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {
    private final UserService userservice;
    private final RouterService router;
    private final Session session;
    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);

    @Override
    public void start(Scanner sc) {
     String username="";
     String password="";
     logger.info("Start registration process...");

     exit:{
        while(true){
            clearScreen();
            System.out.println("WELCOME TO REGISTRATION PAGE");
            sc.nextLine();

            //get username
            username=getUsername(sc);
            
            //get password
            password=getPassword(sc);
            if(password.equals("x")){
                logger.info("Exit registration Screen");
                break exit;
            }
            //confirm user's info
            clearScreen();
                System.out.println("Please confirm your credentials:");
                System.out.println("\nUsername: " + username);
                System.out.println("Password: " + password);
                System.out.print("\nEnter (y/n): ");

                switch (sc.nextLine()) {
                    case "y":
                    logger.info("User confirm credentials are correct");
                        User createdUser = userservice.register(username, password);
                        session.setSession(createdUser);
                        router.navigate("/product", sc);
                        break exit;

                    case "n":
                    logger.info("restarting registration process...");
                        clearScreen();
                        System.out.println("Restarting process...");
                        System.out.print("\nPress enter to continue...");
                        sc.nextLine();
                        break;
                    default:
                    logger.warn("Invalid option");
                        clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        sc.nextLine();
                        break;
                }

            //break out if info is correct
            break exit;  

        }
     }
     
    }


 /*-----------------------HELPER METHOD---------------- */

 public String getUsername(Scanner sc){
    String username="";
    while(true){
        System.out.println("Enter user name: ");
        username=sc.nextLine();

        if(!userservice.isValidUsername(username)){
            logger.warn("Invalid username for: {}",username);
            clearScreen();
            System.out.println("Username needs tobe 8-20 char long");
            System.out.println("\nPress Enter to continue... ");
            sc.nextLine();
            continue;
    }
        break;
    }

    return username;
}
 
 public String getPassword(Scanner sc){
    String password ="";
    String confirmPassword="";
    while (true) {
        System.out.print("\nEnter a password (x to cancel): ");
        password = sc.nextLine();

        if (password.equalsIgnoreCase("x")) {
            return "x";
        }

        if (!userservice.isValidPassword(password)) {
            clearScreen();
            System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
            System.out.print("\nPress enter to continue...");
            sc.nextLine();
            continue;
        }

        System.out.print("\nPlease confirm password (x to cancel): ");
        confirmPassword = sc.nextLine();

        if (confirmPassword.equalsIgnoreCase("x")) {
            return "x";
        }

        if (!userservice.isSamePassword(password, confirmPassword)) {
            clearScreen();
            System.out.println("Passwords do not match");
            System.out.print("\nPress enter to continue...");
            sc.nextLine();
            continue;
        }
        break;
    }
    return password;

 }

    private void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    
    
}

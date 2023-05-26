package com.revature.app.screens;

import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.utils.Session;
import com.revature.app.models.User;
import com.revature.app.services.LoginService;
import com.revature.app.services.RouterService;
import com.revature.app.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginScreen implements IScreen{
    private final LoginService loginservice;
    private final RouterService router;
    private final Session session;
    //private final User user;
    private static final Logger logger = LogManager.getLogger(LoginScreen.class);
    


@Override
public void start(Scanner sc) {
    String username = "";
    String password = "";
    logger.info("Start login process...");

    exit: {
        while (true) {
            //clearScreen();
            System.out.println("WELCOME TO LOGIN PAGE");
            //sc.nextLine();

            // Get username
            username = getUsername(sc);

            // Get password
            password = getPassword(sc);
            //System.out.println(username);
            //System.out.println(password);
            try {
                User user = loginservice.login(username);
                if (user != null) {
                    String storedSalt = user.getSalt();
                    String storedHashedPassword = user.getPassword();

                    boolean passwordsMatch = storedSalt != null && BCrypt.checkpw(password, storedHashedPassword);
                    System.out.println(passwordsMatch);
                
                    if (passwordsMatch) {
                        System.out.println("YOU ARE SUCCESSFULLY LOGGED IN TO E-SHOPPING");
                        logger.info("logged...");
                        while (true) {
                            clearScreen();
                            System.out.println("LOGIN SUCCESSFUL");
                            System.out.println("1. View Products");
                            System.out.println("2. Logout");

                            System.out.print("Enter your choice: ");
                            int choice = sc.nextInt();
                            sc.nextLine(); 

                            switch (choice) {
                                case 1:
                                    router.navigate("/product", sc);
                                    break;
                                case 2:
                                    logger.info("Logout");
                                    break ;
                                default:
                                    System.out.println("Invalid choice");
                            }
                        }
                    } else {
                        // Invalid credentials
                        System.out.println("INVALID PASSWORD");
                    }
                } else {
                    // Invalid username
                    System.out.println("INVALID USERNAME");
                }

                break exit;
            } catch (IOException | ClassNotFoundException e) {
                logger.error("Error occurred during login: {}", e.getMessage());
                break exit;
            }
        }
    }
}

    /*-----------------------HELPER METHOD---------------- */
    public String getUsername(Scanner sc){
        String username="";
        //while(true){
            System.out.println("Enter user name: ");
            username=sc.nextLine();
    
            /*if(!userservice.isValidUsername(username)){
                logger.warn("Invalid username for: {}",username);
                clearScreen();
                System.out.println("Username needs tobe 8-20 char long");
                System.out.println("\nPress Enter to continue... ");
                sc.nextLine();
                continue;
        }
            break;
        }*/
    
        return username;
    }
     
     public String getPassword(Scanner sc){
        String password ="";
       // while (true) {
            System.out.print("\nEnter a password: ");
            password = sc.nextLine();

     /* 
    
            if (!userservice.isValidPassword(password)) {
                clearScreen();
                System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                System.out.print("\nPress enter to continue...");
                sc.nextLine();
                continue;
            }
    
           
            break;*/
       // }
        return password;
    
     }
    
        private void clearScreen(){
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }
        
        
    }


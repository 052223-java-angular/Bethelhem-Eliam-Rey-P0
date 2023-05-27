package com.revature.app.screens;

import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.app.utils.Session;
import com.revature.app.models.User;
import com.revature.app.services.RouterService;
import com.revature.app.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginScreen implements IScreen{
    private final UserService userservice;
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
            sc.nextLine();

            // Get username
            username = getUsername(sc);

            // Get password
            password = getPassword(sc);
            try {
                User user = userservice.login(username);
                String storedHashedPassword = user.getPassword();
                boolean passwordsMatch = BCrypt.checkpw(password, storedHashedPassword);
                if ((user != null) && passwordsMatch) {
                      clearScreen();
                      logger.info("------LOGGED IN -----");
                        
                            System.out.println("YOU ARE SUCCESSFULLY LOGGED IN TO E-SHOPPING");
                            System.out.println();
                            System.out.println("*************************************************************************************");
                            System.out.println("*************************************************************************************");
                            System.out.println();
                            System.out.println("1. View Products");
                            System.out.println("2. Logout");
                            System.out.println();

                            System.out.print("Enter your choice: ");
                            System.out.println();
                            int choice = sc.nextInt();
                            sc.nextLine(); 

                            switch (choice) {
                                case 1:
                                    clearScreen();
                                    router.navigate("/product", sc);
                                    break;
                                case 2:
                                    logger.info("Logout");
                                    router.navigate("/logout", sc);
                                    break ;
                                default:
                                    System.out.println("Invalid choice");
                            }
                        }
                    else  {
                        // Invalid credentials
                        clearScreen();
                        System.out.println("INCORRECT USER OR PASSWORD ");
                        
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
        while (true) {
            System.out.print("\nEnter a password: ");
            password = sc.nextLine();



    
            if (!userservice.isValidPassword(password)) {
                clearScreen();
                System.out.println("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                System.out.print("\nPress enter to continue...");
                sc.nextLine();
                continue;
            }
    
           
            break;
        }
        clearScreen();
        return password;
    
     }
    
        private void clearScreen(){
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }
        
        
    }


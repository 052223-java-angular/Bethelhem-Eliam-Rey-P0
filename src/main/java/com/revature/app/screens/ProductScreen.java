package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.utils.Session;

public class ProductScreen implements IScreen{
    private Session session;

    /**
     * Constructs a new MenuScreen with the specified Session.
     *
     * the Session containing user information
     */
    public ProductScreen(Session session) {
        this.session = session;
    }

    @Override
    public void start(Scanner sc) {
        System.out.println("Welcome to the Product screen, " + session.getUsername() + "!");
        sc.nextLine();
    }

/* 

    private void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    */
}

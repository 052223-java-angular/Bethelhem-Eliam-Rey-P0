package com.revature.app.screens;

import java.util.Scanner;

import com.revature.app.utils.Session;

public class ProductScreen implements IScreen{
    private Session session;

    public ProductScreen(Session session) {
        this.session = session;
    }

    @Override
    public void start(Scanner sc) {
        System.out.println("Welcome to the Product screen, " + session.getUsername() + "!");
        sc.nextLine();
    }

}

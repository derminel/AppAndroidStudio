package com.example.secondtest;

import java.util.ArrayList;
import android.content.Context;

public class WishList {
    private String name ;
    private String listNumber ;
    private WishListDAO DAO;
    private boolean publicAccess ;
    private String description ;
    private String recipient ;
    private String creator ;  //recoit un login
    private ArrayList<String> admins ;
    private ArrayList<String> invisibles ;
    private ArrayList<String> readers ;
    private ArrayList<Product> products;

    public WishList(String naame, boolean pA,Context context) {
        this.name = naame;
        //this.listNumber = lNb;
        this.publicAccess = pA;
        this.DAO = new WishListDAO(context);
        //this.description = d;
        //this.recipient = r;
        this.admins = new ArrayList<String>();
        //this.admins.add(c);
        this.invisibles = new ArrayList<String>();
        this.readers = new ArrayList<String>();
        this.products = new ArrayList<Product>();
    }

    public void addProduct(Product p) {
        this.products.add(p) ;
    }

    public void deleteProduct (Product p) {
        this.products.remove(p) ;
    }

    public double getPrice() {
        double tot = 0 ;
        for (Product p : this.products){
            tot += p.getPrice() ;
        }
        return tot ;
    }

    public void addAdmin(String login) {
        this.admins.add(login);
    }

    public void addReader(String login) {
        this.readers.add(login);
    }

    public void addInvisible(String login) {
        this.invisibles.add(login);
    }

    public void deleteAdmin(String login) {
        this.admins.remove(login);
    }

    public void deleteReader(String login) {
        this.readers.add(login);
    }

    public void deleteInvisible(String login) {
        this.invisibles.add(login);
    }

    public boolean isAdmin(String login) {
        return this.admins.contains(login) ;
    }

    public boolean isReader(String login) {
        return this.readers.contains(login) ;
    }

    public boolean isInWishList(Product p){
        return this.products.contains(p) ;
    }
}
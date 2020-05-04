package com.example.secondtest;

import android.content.Context;

import java.util.ArrayList;

public class WishList {
    private WishlistDAO wishlistDAO;
    private ModifierDAO modifierDAO;
    private String name ;
    private String listNumber ;
    private boolean publicAccess ;
    private String description ;
    private String recipient ;
    private String creator ;  //recoit un login
    private ArrayList<String> admins ;
    private ArrayList<String> invisibles ;
    private ArrayList<String> readers ;
    private ArrayList<Product> products;


    public WishList(String n, String lNb, boolean pA, String d, String r, String c, Context context) {
        this.name = n;
        this.listNumber = lNb;
        this.publicAccess = pA;
        this.description = d;
        this.recipient = r;
        this.admins = new ArrayList<>();
        this.admins.add(c);
        this.invisibles = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.products = new ArrayList<>();
        this.wishlistDAO = new WishlistDAO(context);
        this.modifierDAO = new ModifierDAO(context);

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
        this.admins = modifierDAO.usersThatAreStatus(this.listNumber,"Admin");
        return this.admins.contains(login) ;
    }

    public boolean isReader(String login) {
        this.readers = modifierDAO.usersThatAreStatus(this.listNumber,"Reader");
        return this.readers.contains(login) ;
    }

    //permet de savoir si un produit est déja la
    public boolean isInWishList(Product p){
        this.products = contentDAO;
        return this.products.contains(p) ;
    }
}
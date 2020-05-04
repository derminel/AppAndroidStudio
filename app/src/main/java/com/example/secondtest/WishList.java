package com.example.secondtest;

import android.content.Context;

import java.util.ArrayList;

public class WishList {
    private WishListDAO wishlistDAO;
    private ModifierDAO modifierDAO;
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
    private ContentDAO contentDAO;


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
        this.wishlistDAO = new WishListDAO(context);
        this.modifierDAO = new ModifierDAO(context);
        this.contentDAO = new ContentDAO(context);

    }

    public void addProduct(Product p) {
        contentDAO.addProduct(p.getNb(),this.listNumber);
        this.products.add(p) ;
    }

    public void deleteProduct (Product p) {
        contentDAO.delProd(this.listNumber,p.getNb());
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
        modifierDAO.addStatus(login,this.listNumber,"Admin");
        this.admins.add(login);
    }

    public void addReader(String login) {
        modifierDAO.addStatus(login,this.listNumber,"Reader");
        this.readers.add(login);
    }

    public void addInvisible(String login) {
        modifierDAO.addStatus(login,this.listNumber,"Invisible");
        this.invisibles.add(login);
    }

    public void deleteAdmin(String login) {
        modifierDAO.delStatus(this.listNumber, login);
        this.admins.remove(login);
    }

    public void deleteReader(String login) {
        modifierDAO.delStatus(this.listNumber, login);
        this.readers.remove(login);
    }

    public void deleteInvisible(String login) {
        modifierDAO.delStatus(this.listNumber, login);
        this.invisibles.remove(login);
    }

    public boolean isAdmin(String login) {
        this.admins = modifierDAO.usersThatAreStatus(this.listNumber,"Admin");
        return this.admins.contains(login) ;
    }

    public boolean isReader(String login) {
        this.readers = modifierDAO.usersThatAreStatus(this.listNumber,"Reader");
        return this.readers.contains(login) ;
    }

    public boolean isInWishList(Product p){
        return this.products.contains(p) ;
    }
}
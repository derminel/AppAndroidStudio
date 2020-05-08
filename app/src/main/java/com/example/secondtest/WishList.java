package com.example.secondtest;

import java.util.ArrayList;
import android.content.Context;

public class WishList {
    private String name ;
    private String listNumber ;
    private WishListDAO wishlistDAO;
    private ProductDAO productDAO;
    private ContentDAO contentDAO;
    private boolean publicAccess ;
    private String description ;
    private String recipient ;
    private String creator ;  //recoit un login
    private ArrayList<String> admins ;
    private ArrayList<String> invisibles ;
    private ArrayList<String> readers ;
    private ArrayList<Product> products;

    public WishList(String name, boolean publicAccess,Context context) {
        this.name = name;
        this.wishlistDAO = new WishListDAO(context);
        this.contentDAO = new ContentDAO(context);
        this.productDAO = new ProductDAO(context);
        this.listNumber = this.setListNumber();
        this.publicAccess = publicAccess;
        //this.description = d;
        //this.recipient = r;
        this.admins = new ArrayList<String>();
        //this.admins.add(c);
        this.invisibles = new ArrayList<String>();
        this.readers = new ArrayList<String>();
        this.products = new ArrayList<Product>();
    }

    public void addProduct(Product p) {
        //this.wishlistDAO.addProduct(this.listNumber, p);
        this.products.add(p) ;
    }

    public void deleteProduct (Product p) {
        this.products.remove(p) ;
    }

    private String setListNumber() {
        try{
            return "List" + String.valueOf(Integer.parseInt(this.wishlistDAO.lineCounter())+1);
        }
        catch (Exception e){
            return "List1";
        }
    }

    public double getPrice() {
        double tot = 0 ;
        for (Product p : this.products){
            tot += p.getPrice() ;
        }
        return tot ;
    }

    public String getName(){
        return this.name;
    }

    public String getListNb(){
        return this.listNumber;
    }

    public ArrayList<Product> getProducts(String listNb, Context context){
        ArrayList<Product> productsOfTheList = new ArrayList<Product>();
        for(String product : this.contentDAO.getProductsOfAList(listNb)){
            Product newProduct = new Product(productDAO.getProductName(product),0,null,null,null,context);
            productsOfTheList.add(newProduct);
        }
        return productsOfTheList;
    }

    public boolean addAdmin(String login) {
        this.admins.add(login);
        return this.wishlistDAO.addStatus("Admin", login, this.listNumber);
    }

    public boolean addReader(String login) {
        this.readers.add(login);
        return this.wishlistDAO.addStatus("Reader", login, this.listNumber);
    }

    public boolean addInvisible(String login) {
        this.invisibles.add(login);
        return this.wishlistDAO.addStatus("Invisible", login, this.listNumber);
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
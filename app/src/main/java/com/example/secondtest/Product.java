package com.example.secondtest;

import android.content.Context;

public class Product {
    private String productNb ;
    private String name ;
    private double price ;
    private String info ;
    private String category ;
    private String website ;
    private int likes ;
    private ProductDAO productDAO;

    public Product(String n, Double p, String i, String c, String w, Context context) {
        this.productDAO = new ProductDAO(context);
        this.productNb = this.setProductNumber();
        this.name = n ;
        this.price = p ;
        this.info = i ;
        this.category = c ;
        this.website = w ;
        this.likes = 0 ;
    }
    private String setProductNumber() {
        try{
            return "Product" + String.valueOf(Integer.parseInt(this.productDAO.lineCounter())+1);
        }
        catch (Exception e){
            return "Product1";
        }
    }


    public double getPrice() {
        return this.price ;
    }

    public String getProductNb(){
        return this.productNb;
    }

    public String getName(){
        return this.name;
    }
}

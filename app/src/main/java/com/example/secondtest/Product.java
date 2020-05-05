package com.example.secondtest;

public class Product {
    private String productNb ;
    private String name ;
    private double price ;
    private String info ;
    private String category ;
    private String website ;
    private int likes ;

    public Product(String pNb, String n, Double p, String i, String c, String w) {
        this.productNb = pNb ;
        this.name = n ;
        this.price = p ;
        this.info = i ;
        this.category = c ;
        this.website = w ;
        this.likes = 0 ;
    }
    public String getNb() {return this.productNb ; }
    public double getPrice() {
        return this.price ;
    }

    public String getProductNb(){
        return this.productNb;
    }
}

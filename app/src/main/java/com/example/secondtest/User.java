package com.example.secondtest;

import java.util.ArrayList;

public class User {
    private String password ;
    private String login ;
    private ArrayList<User> friends ;
    private Profile profile ;

    public User(String p, String l) {
        this.password = p ;
        this.login = l ;
        this.friends = new ArrayList<User>() ;
        this.profile = new Profile() ;
    }

    public boolean exist(String login){
        //call DAO
    }

    public void setProfile(String n, String l, byte photo, String a, String p) {
        this.profile.setName(n);
        this.profile.setLastname(l);
        this.profile.setPhoto(photo);
        this.profile.setAddress(a);
        this.profile.setPreferences(p);
    }

    public boolean addAProduct(WishList w, Product p){
        w.addProduct(p);
    }

    public void addWishlist(String){
        //Ajouter une wishlist a la DAO
    }

    public boolean connection(String )
}

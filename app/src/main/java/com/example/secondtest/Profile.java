package com.example.secondtest;

public class Profile {
    private String name ;
    private String lastname ;
    private byte[] photo ;
    private String address ;
    private String preferences ;

    public Profile(){
        this.name = null ;
        this.lastname = null ;
        this.photo = null ;
        this.address = null ;
        this.preferences = null ;
    }

    public void setName(String n){
        this.name = n ;
    }

    public void setLastname(String l){
        this.lastname = l ;
    }

    public void setPhoto(byte[] p){
        this.photo = p ;
    }

    public void setAddress(String a){
        this.address = a ;
    }

    public void setPreferences(String p){
        this.preferences = p ;
    }
}

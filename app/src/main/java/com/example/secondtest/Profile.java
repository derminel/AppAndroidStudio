package com.example.secondtest;


import android.content.Context;

public class Profile {
    private String name ;
    private String lastname ;
    private byte[] photo ;
    private String address ;
    private String preferences ;
    private ProfileDAO profileDAO;

    public Profile(String login, Context context){
        profileDAO = new ProfileDAO(context);
        this.name = profileDAO.findName(login);
        this.lastname = profileDAO.findLastName(login) ;
        this.photo = null;
        this.address = profileDAO.findAddress(login) ;
        this.preferences = profileDAO.findPreference(login);
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

    public String getName() {return this.name;}
    public String getLastname() {return this.lastname;}
    public String getAddress() {return this.address;}
    public String getPreferences() {return this.preferences;}

}

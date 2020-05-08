package com.example.secondtest;

import android.content.Context;

import java.util.ArrayList;

public class User {
    private UserDAO userDAO ;
    private FriendsDAO friendsDAO;
    private String password ;
    private String login ;
    private ArrayList<User> friends ;
    private Profile profile ;

    public User(String p, String l, Context context) {
        this.password = p ;
        this.login = l ;
        this.friends = new ArrayList<User>();
        this.profile = new Profile(this.login, context);
        this.userDAO = new UserDAO(context);
        this.friendsDAO = new FriendsDAO(context);
    }

    public boolean exist(String login){
        return this.userDAO.exist(login);
    }

    public Profile getProfile(){
        return this.profile;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword(){
        return password;
    }

    public void setProfile(String n, String l, byte[] photo, String a, String p) {
        this.profile.setName(n);
        this.profile.setLastname(l);
        this.profile.setPhoto(photo);
        this.profile.setAddress(a);
        this.profile.setPreferences(p);
    }

    public boolean addAProduct(WishList w, Product p){
        w.addProduct(p);
        return true;
    }

    public void addWishlist(String w){
        //Ajouter une wishlist a la DAO
    }

    //verifie que le password du login est bien correct
    public boolean connection(String login, String password){
        try{
            return this.userDAO.getPassword(login).equals(password);
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean friendRequest(String login){
        return this.friendsDAO.addFriends(this.login, login);
    }


    public ArrayList<User> getFriends(Context context){
        ArrayList<User> friends = new ArrayList<User>();
        for (String friend : this.friendsDAO.getFriends(this.getLogin())){
            friends.add(new User(null,friend,context));
        }
        return friends;
    }

    public ArrayList<User> getRequests(Context context){
        ArrayList<User> requests = new ArrayList<User>();
        for (String request : this.friendsDAO.getRequests(this.getLogin())){
            requests.add(new User(null,request,context));
        }
        return requests;
    }

    public void signIn(String login, String password){
        this.login = login;
        this.password = password;
    }
}

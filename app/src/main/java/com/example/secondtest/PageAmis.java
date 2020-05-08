package com.example.secondtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class PageAmis extends AppCompatActivity {

    private FriendsDAO friendsDAO;
    private UserDAO userDAO;
    private String login;
    private User user;

    private CustomAdapterFriends adapter;
    private SearchView searchView;

    private boolean canInit = true;



    //Creation de la page pour voir ses amis
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showToast(login);
        super.onCreate(savedInstanceState);
        this.login = getIntent().getStringExtra("Login");
        this.user = new User(null,login,this);
        this.userDAO = new UserDAO(this);
        this.friendsDAO = new FriendsDAO(this);

        setContentView(R.layout.activity_page_amis);
        this.searchView = findViewById(R.id.SearchbarFriends);
        if(canInit){
            initList();
            canInit = false;
        }

        configuresearchbar();
        configureAddButton();
        configureBack();
        configureFriendRequest();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private void start(Class<?> cls){
        Intent page = new Intent(PageAmis.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }

    //barre de recherche des amis
    private void configuresearchbar(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void initList(){
        ListView listView = findViewById(R.id.ListViewFriends);
        ArrayList<User> friends = this.user.getFriends(this);
        adapter=new CustomAdapterFriends(this,
                R.layout.row_friends, friends, login);
        listView.setAdapter(adapter);
    }

    private void configureFriendRequest(){
        Button friendsRequestsButton = findViewById(R.id.friendsRequestsButton);
        friendsRequestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { start(PagePourGererDmdAmis.class); }
        });
    }

    //bouton pour ajouter des amis
    private void configureAddButton() {
        Button addFriend = findViewById(R.id.addFriendButton);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getLogin().equals(searchView.getQuery().toString())) {
                    showToast("You can't add yourself as friend");
                } else if (!userDAO.getUserLoginDb().contains(searchView.getQuery().toString())) {
                    showToast("This login doesn't exist");
                } else if (friendsDAO.areFriends(user.getLogin(), searchView.getQuery().toString())) {
                    showToast("You're already friends");
                } else {
                    if (user.friendRequest(searchView.getQuery().toString())) { showToast("Invitation has been sent");
                    } else { showToast("Problem with your invitation, try again"); }
                }
            }
        });
    }

    private void configureBack(){
        Button goBackButton = findViewById(R.id.GoBackFriends);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { start(PageAccueil.class); }
        });
    }
}

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

    private ArrayList<User> friends;
    private CustomAdapterFriends adapter;
    private ListView listView;
    private SearchView searchView;
    private Button addButton;
    private Button friendsRequestsButton;
    private Button goBackButton;

    private boolean canInit = true;
    private UserDAO userDAO;
    private User user;
    private String login;
    private String loginAfterFriendRequests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_amis);

        this.listView=(ListView)findViewById(R.id.ListViewFriends);
        this.searchView=(SearchView) findViewById(R.id.SearchbarFriends);
        this.addButton=(Button) findViewById(R.id.addFriendButton);
        this.friendsRequestsButton = (Button) findViewById(R.id.friendsRequestsButton);
        this.goBackButton = (Button) findViewById(R.id.GoBackFriends);

        this.login = getIntent().getStringExtra("LOGIN_AMIS");
        this.loginAfterFriendRequests = getIntent().getStringExtra("LOGIN_AMIS_APRES_DMD_AMIS");
        if(loginAfterFriendRequests != null){
            login = loginAfterFriendRequests;
        }

        this.user = new User(null,login,this);
        this.userDAO = new UserDAO(this);

        if(canInit){
            initList();
            canInit = false;
        }
        showToast(login);

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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(login);
                Intent intent = new Intent(PageAmis.this, PagePourAjouterUnAmi.class) ;
                intent.putExtra("LOGIN_AJOUT_AMI", login);
                startActivity(intent);
            }
        });

        goBack();
        friendsRequests();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void friendsRequests(){
        friendsRequestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Login" + login);
                Intent intent = new Intent(PageAmis.this, PagePourGererDmdAmis.class);
                intent.putExtra("LOGIN_DEMAMDES_AMIS", login);
                startActivity(intent);
            }
        });
    }

    private void initList(){
        this.friends = this.user.getFriends(this);
        adapter=new CustomAdapterFriends(this,
                R.layout.row_friends, friends, login);
        listView.setAdapter(adapter);
    }

    private void goBack(){
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageAmis.this, PageAccueil.class);
                intent.putExtra("LOGIN_ACCEUIL_APRES_VISITE_AMIS", login);
                startActivity(intent);
            }
        });
    }
}

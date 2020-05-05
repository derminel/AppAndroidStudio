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
    public static final String EXTRA_LOGIN_3 = "com.example.application.example.EXTRA_TEXT";

    private ArrayList<User> friends;
    private CustomAdapterFriends adapter;
    private ListView listView;
    private SearchView searchView;
    private Button addButton;

    private boolean canInit = true;
    private UserDAO userDAO;
    private String login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_amis);

        listView=(ListView)findViewById(R.id.ListViewFriends);
        searchView=(SearchView) findViewById(R.id.SearchbarFriends);
        addButton=(Button) findViewById(R.id.addFriendButton);

        Intent intent = getIntent();
        this.login = intent.getStringExtra(PageAccueil.EXTRA_LOGIN_2);

        this.userDAO = new UserDAO(this);

        if(canInit){
            initList();
            canInit = false;
        }

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
                Intent intent = new Intent(PageAmis.this, PagePourAjouterUnAmi.class) ;
                intent.putExtra(Intent.EXTRA_TEXT, login);
                startActivity(intent);
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void initList(){
        this.friends = new ArrayList<User>();
        for (String elem : this.userDAO.getUserLoginDb()){
            if (!(elem.equals(this.login))){
                User newFriend = new User(null, elem, this);
                this.friends.add(newFriend);
            }
        }
        adapter=new CustomAdapterFriends(this,
                R.layout.row_friends, friends);
        listView.setAdapter(adapter);
    }
}

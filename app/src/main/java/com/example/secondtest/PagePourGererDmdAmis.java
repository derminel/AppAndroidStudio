package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class PagePourGererDmdAmis extends AppCompatActivity {

    private ArrayList<User> friendsRequests;
    private CustomAdapterFriendsRequests adapter;
    private ListView listView;

    private boolean canInit = true;
    private String login;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_gerer_dmd_amis);

        listView=(ListView)findViewById(R.id.ListViewFriendsRequests);

        this.login = getIntent().getStringExtra("LOGIN4");
        this.user = new User(null, login, this);

        if(canInit){
            initList();
            canInit = false;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void initList(){
        this.friendsRequests = this.user.getRequests(this);
        adapter=new CustomAdapterFriendsRequests(this,
                R.layout.row_friendrequest, friendsRequests, login);
        listView.setAdapter(adapter);
    }
}

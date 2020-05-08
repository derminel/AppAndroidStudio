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
    private String login;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.login = getIntent().getStringExtra("Login");
        this.user = new User(null, login, this);

        setContentView(R.layout.activity_page_pour_gerer_dmd_amis);
        this.listView = findViewById(R.id.ListViewFriendsRequests);
        initList();
        goBack();
    }

    private void start(Class<?> cls){
        Intent page = new Intent(PagePourGererDmdAmis.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private void goBack() {
        Button goBack = findViewById(R.id.GoBackFriendRequest);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getStringExtra("from").equals("Amis")){
                    start(PageAmis.class);
                } else{ start(PageAccueil.class); }
            }
        });
    }

    private void initList(){
        this.friendsRequests = this.user.getRequests(this);
        if(this.friendsRequests.size()==0){
            showToast("you have no friend requests");
        }
        adapter=new CustomAdapterFriendsRequests(this, R.layout.row_friendrequest, friendsRequests, login);
        listView.setAdapter(adapter);
    }
}

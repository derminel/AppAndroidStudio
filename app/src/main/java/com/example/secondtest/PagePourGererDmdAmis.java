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
    private Button goBack;

    private String login;
    private String loginPopUp;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_gerer_dmd_amis);

        this.listView=(ListView)findViewById(R.id.ListViewFriendsRequests);
        this.goBack = (Button) findViewById(R.id.GoBackFriendRequest);

        this.login = getIntent().getStringExtra("LOGIN_DEMAMDES_AMIS");
        this.loginPopUp = getIntent().getStringExtra("LOGIN_GERER_AMIS");
        if(loginPopUp != null){
            login = loginPopUp;
        }
        this.user = new User(null, login, this);

        showToast(login+"|"+loginPopUp);
        initList();
        goBack();
    }

    private void goBack() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginPopUp == null){
                    Intent intent = new Intent(PagePourGererDmdAmis.this, PageAmis.class);
                    intent.putExtra("LOGIN_AMIS_APRES_DMD_AMIS", login);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(PagePourGererDmdAmis.this, PageAccueil.class);
                    intent.putExtra("LOGIN_AMIS_APRES_POP_UP_AMIS", login);
                    startActivity(intent);
                }
            }
        });
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

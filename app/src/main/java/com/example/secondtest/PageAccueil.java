package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PageAccueil extends AppCompatActivity implements View.OnClickListener {

    private String login;
    private User user;
    private CardView wishlistsCardView;
    private CardView friendsCardView;
    private CardView profileCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page_accueil);

        this.login = getIntent().getStringExtra("Login");
        this.user = new User(null, login, this);

        this.wishlistsCardView = findViewById(R.id.wishlistCardView);
        this.friendsCardView = findViewById(R.id.friendsCardView);
        this.profileCardView = findViewById(R.id.profileCardView);

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        if(user.getRequests(this).size() > 0){ openDialog(view); }

        wishlistsCardView.setOnClickListener(this);
        friendsCardView.setOnClickListener(this);
        profileCardView.setOnClickListener(this);

        showToast(login);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void start(Intent gotoapage){
        gotoapage.putExtra("Login", login);
        startActivity(gotoapage);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.wishlistCardView :
                Intent gotoWishlist = new Intent(PageAccueil.this, PagesListesDeSouhaits.class);
                start(gotoWishlist) ; break;
            case R.id.friendsCardView :
                Intent gotoFriends = new Intent(PageAccueil.this, PageAmis.class);
                start(gotoFriends); break;
            case R.id.profileCardView :
                Intent gotoProfil = new Intent(PageAccueil.this, PageProfil.class);
                start(gotoProfil); break;
        }
    }

    public void openDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to go to friends' requests ?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(PageAccueil.this, PagePourGererDmdAmis.class);
                intent.putExtra("Login", login);
                startActivity(intent);
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

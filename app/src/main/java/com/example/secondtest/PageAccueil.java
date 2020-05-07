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
    private String loginAfterCreate;
    private String loginAfterSetProfile;
    private String loginAfterSetFriend;
    private User user;
    private CardView wishlistsCardView;
    private CardView friendsCardView;
    private CardView profileCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);

        this.login = getIntent().getStringExtra("LOGIN_ACCUEIL");
        this.loginAfterCreate = getIntent().getStringExtra("LOGIN_ACCUEIL_APRES_CREATION");
        this.loginAfterSetProfile = getIntent().getStringExtra("LOGIN_ACCEUIL_APRES_VISITE_PROFIL");
        this.loginAfterSetFriend = getIntent().getStringExtra("LOGIN_ACCEUIL_APRES_VISITE_AMIS");
        this.user = new User(null, login, this);

        if (loginAfterCreate != null){
            this.login = loginAfterCreate;
        }
        else if(loginAfterSetProfile != null){
            login = loginAfterSetProfile;
        }
        else if(loginAfterSetFriend != null){
            login = loginAfterSetFriend;
        }

        this.wishlistsCardView = (CardView) findViewById(R.id.wishlistCardView);
        this.friendsCardView = (CardView) findViewById(R.id.friendsCardView);
        this.profileCardView = (CardView) findViewById(R.id.profileCardView);

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        if(user.getRequests(this).size() > 0){
            openDialog(view);
        }

        wishlistsCardView.setOnClickListener(this);
        friendsCardView.setOnClickListener(this);
        profileCardView.setOnClickListener(this);

        showToast(login);
    }

    @Override
    public void onClick(View v){
        Intent intent;

        switch (v.getId()){
            case R.id.wishlistCardView : intent = new Intent(PageAccueil.this, PagesListesDeSouhaits.class);
                intent.putExtra("LOGIN_LISTES_DE_SOUHAITS", login); startActivity(intent); break;
            case R.id.friendsCardView : intent = new Intent(PageAccueil.this, PageAmis.class);
                intent.putExtra("LOGIN_AMIS", login); startActivity(intent); break;
            case R.id.profileCardView : intent = new Intent(PageAccueil.this, PageProfil.class);
                intent.putExtra("LOGIN_PROFIL", login); startActivity(intent); break;
            default:break;
        }
    }

    public void openDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to go to friends' requests ?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(PageAccueil.this, PagePourGererDmdAmis.class);
                intent.putExtra("LOGIN_GERER_AMIS", login);
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

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

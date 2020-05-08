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

    //Creation de la page d'accueil
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.login = getIntent().getStringExtra("Login");
        this.user = new User(null, login, this);

        setContentView(R.layout.activity_page_accueil);
        this.wishlistsCardView = findViewById(R.id.wishlistCardView);
        this.friendsCardView = findViewById(R.id.friendsCardView);
        this.profileCardView = findViewById(R.id.profileCardView);

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        if(user.getRequests(this).size() > 0){ openDialog(view); }

        wishlistsCardView.setOnClickListener(this);
        friendsCardView.setOnClickListener(this);
        profileCardView.setOnClickListener(this);

        exitButton();
    }

    private void exitButton() {
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogExitButton(v);
            }
        });
    }

    //Creation du message d'erreur
    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void start(Class<?> cls){
        Intent page = new Intent(PageAccueil.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }

    //Boutons pour selectionner soit profil, amis ou wishlist
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.wishlistCardView :
                start(PagesListesDeSouhaits.class);
                break;
            case R.id.friendsCardView :
                start(PageAmis.class);
                break;
            case R.id.profileCardView :
                start(PageProfil.class);
                break;
            default:
                break;
        }
    }

    //Si une demande d'ami est en attente
    public void openDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this,R.style.AlertDialog);
        alertDialogBuilder.setMessage("Do you want to go to friends' requests ?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                start(PagePourGererDmdAmis.class);
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

    public void openDialogExitButton(View view) {
        AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this,R.style.AlertDialog);
        alertDialogBuilder.setMessage("Are you sure you want to log out ?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(PageAccueil.this, MainActivity.class);
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

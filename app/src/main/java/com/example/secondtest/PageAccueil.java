package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PageAccueil extends AppCompatActivity {

    private String login;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);

        this.login = getIntent().getStringExtra("LOGIN_ACCUEIL");
        this.user = new User(null, login, this);

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        if(user.getRequests(this).size() > 0){
            openDialog(view);
        }

        configureNextButtonProfile();
        configureNextButtonWishLists();
        configureNextButtonFriends();

    }

    private void configureNextButtonProfile() {
        Button nextButton = (Button)  findViewById(R.id.Profile);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageAccueil.this, PageProfil.class);
                intent.putExtra("LOGIN_PROFIL", login);
                startActivity(intent);
            }
        });
    }

    private void configureNextButtonWishLists() {
        Button nextButton = (Button)  findViewById(R.id.Wishlists);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(PageAccueil.this, PagesListesDeSouhaits.class));
            }
        });
    }
    private void configureNextButtonFriends() {
        Button nextButton = (Button)  findViewById(R.id.Friends);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageAccueil.this, PageAmis.class);
                intent.putExtra("LOGIN2", login);
                startActivity(intent);
            }
        });
    }

    public void openDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to go to friends' requests ?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(PageAccueil.this, PagePourGererDmdAmis.class);
                intent.putExtra("LOGIN4", login);
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

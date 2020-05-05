package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageAccueil extends AppCompatActivity {
    public static final String EXTRA_LOGIN_2 = "com.example.application.example.EXTRA_TEXT";

    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);

        Intent intent = getIntent();
        this.login = intent.getStringExtra(MainActivity.EXTRA_LOGIN);

        configureNextButtonProfile();
        configureNextButtonWishLists();
        configureNextButtonFriends();
    }

    private void configureNextButtonProfile() {
        Button nextButton = (Button)  findViewById(R.id.Profile);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(PageAccueil.this, PageProfil.class));
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
                intent.putExtra(Intent.EXTRA_TEXT, login);
                startActivity(intent);
            }
        });
    }

}

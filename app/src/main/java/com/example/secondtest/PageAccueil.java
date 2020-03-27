package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);

        configureNextButtonProfile();
        configureNextButtonWishLists();
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
}
